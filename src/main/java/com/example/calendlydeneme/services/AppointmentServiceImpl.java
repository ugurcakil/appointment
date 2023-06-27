package com.example.calendlydeneme.services;

import com.example.calendlydeneme.entities.Appointment;
import com.example.calendlydeneme.entities.User;
import com.example.calendlydeneme.repositories.AppointmentRepository;
import com.example.calendlydeneme.repositories.RoleRepository;
import com.example.calendlydeneme.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    RoleRepository roleRepo;

    @Autowired
    AppointmentRepository appRepo;


    public AppointmentServiceImpl(AppointmentRepository appointmentRepository){
        super();
        this.appRepo= appointmentRepository;
    }

    public void createAppointment(Appointment appointment, Long doc_id){
        appointment.setAppStatusTaken(false);
        User doc = userRepo.findBy(doc_id);
        appointment.setAppDoctor(doc);
    }

    public void takeAppointment(Appointment appointment, Long pat_id, Long app_id){
        appointment = appRepo.findBy(app_id);
        User pat = userRepo.findBy(pat_id);
        appointment.setPatient(pat);
        if(appointment.getPatient()!= null){
            appointment.setAppStatusTaken(true);
        }
    }

    public List<Appointment> listAllAppointments(){return appRepo.findAll();}

    public Appointment get(Long id){return appRepo.findById(id).get();}


    public List<Appointment> listAvailable(){return appRepo.listAvailableAppointments();}


    //public List<User> listDoctors(){}


}
