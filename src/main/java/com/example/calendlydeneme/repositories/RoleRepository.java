package com.example.calendlydeneme.repositories;

import com.example.calendlydeneme.entities.Appointment;
import com.example.calendlydeneme.entities.Role;
import com.example.calendlydeneme.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);

}
