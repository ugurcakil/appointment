package com.example.calendlydeneme.services;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.calendlydeneme.entities.Role;
import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.AppointmentRepository;
import com.example.calendlydeneme.repositories.RoleRepository;
import com.example.calendlydeneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    AppointmentRepository appRepo;



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;



    public UserServiceImpl(UserRepository userRepository) {
        super();
        this.userRepo = userRepository;
    }




    public void registerDefaultUser(User user) {
        Role roleCustomer = roleRepo.findByName("Customer");
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        user.addRole(roleCustomer);

        userRepo.save(user);
    }

    public List<User> listAll() {
        return userRepo.findAll();
    }



    public User get(Long id) {
        return userRepo.findById(id).get();
    }

    public List<Role> listRoles() {
        return roleRepo.findAll();
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password![1]");
        }

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    public void save(User user) {
        userRepo.save(user);
    }










}
