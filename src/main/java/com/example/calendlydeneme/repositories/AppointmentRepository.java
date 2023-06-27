package com.example.calendlydeneme.repositories;

import com.example.calendlydeneme.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    @Query(value = "select u from Appointment u where u.id = ?1")
    Appointment findBy(Long id);

    @Query(nativeQuery = true, value = "select * from Appointment u where u.appStatusTaken = false")
    List<Appointment> listAvailableAppointments();

}

