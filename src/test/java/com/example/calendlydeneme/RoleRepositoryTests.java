/*package com.example.calendlydeneme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.calendlydeneme.entities.Role;
import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.RoleRepository;
import com.example.calendlydeneme.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired private RoleRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;


    @Test
    public void testCreateRoles() {
        Role doctor = new Role("Doctor");
        Role assistant = new Role("Assistant");
        Role admin = new Role("Admin");
        Role customer = new Role("Customer");

        repo.saveAll(List.of(doctor, assistant, admin, customer));

        List<Role> listRoles = repo.findAll();

        assertThat(listRoles.size()).isEqualTo(4);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("12345678");
        user.setFirstName("Ugur");
        user.setLastName("Cakil");

        User savedUser = userRepo.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(user.getEmail()).isEqualTo(existUser.getEmail());

    }

    @Test
    public void testAddRoleToNewUser() {
        Role roleAdmin = roleRepo.findByName("Admin");

        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setPassword("12345678");
        user.setFirstName("admin");
        user.setLastName("admin");
        user.addRole(roleAdmin);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(1);
    }

    @Test
    public void testAddRoleToExistingUser() {
        User user = userRepo.findById(16L).get();
        Role roleUser = roleRepo.findByName("Admin");

        user.addRole(roleUser);

        User savedUser = userRepo.save(user);

        assertThat(savedUser.getRoles().size()).isEqualTo(2);
    }

}*/
