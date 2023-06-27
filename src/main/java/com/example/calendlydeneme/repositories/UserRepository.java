package com.example.calendlydeneme.repositories;

import com.example.calendlydeneme.entities.Appointment;
import com.example.calendlydeneme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);

    @Query(value = "select u from User u where u.id = ?1")
    User findBy(Long id);

}
