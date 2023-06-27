/*package com.example.calendlydeneme;

import com.example.calendlydeneme.entities.Appointment;
import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.AppointmentRepository;
import com.example.calendlydeneme.repositories.RoleRepository;
import com.example.calendlydeneme.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class AppointmentRepositoryTests {

    @Autowired private AppointmentRepository appRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;


    @Test
    public void testCreateAppointment(){
        Appointment appointment = new Appointment();
        appointment.setAppStatusTaken(false);
        User doc= userRepo.findBy(15L);
        appointment.setAppDoctor(doc);

        Appointment savedAppointment = appRepo.save(appointment);
        Appointment exitAppointment = entityManager.find(Appointment.class, savedAppointment.getId());
        assertThat(appointment.getAppDoctor()).isEqualTo(exitAppointment.getAppDoctor());



    }

    /*@Test
    public void testTakeAppointments(){
        Appointment appointment= appRepo.findBy(1L);
        User pat = userRepo.findBy(L);
        appointment.setPatient(pat);
        if(appointment.getPatient()!= null){
          appointment.setAppStatusTaken(true);
        }
        Appointment savedAppo = appRepo.save(appointment);
        Appointment exitAppo = entityManager.find(Appointment.class, savedAppo.getId());
        assertThat(appointment.isAppStatusTaken()).isEqualTo(exitAppo.isAppStatusTaken());
    }
}*/
