package com.example.calendlydeneme.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean appStatusTaken;

    @OneToOne(targetEntity = User.class, cascade= CascadeType.ALL)
    private User appDoctor;




    @OneToOne(targetEntity = User.class, cascade= CascadeType.ALL)
    @JoinTable(name = "appointments_patient",
            joinColumns = @JoinColumn(name = "app_id"),
            inverseJoinColumns = @JoinColumn(
                    name = "patient_id")
    )
    private User patient;

    public Appointment(){}

    public Appointment(Long id, boolean appStatusTaken, User appDoctor, User patient) {
        this.id = id;
        this.appStatusTaken = appStatusTaken;
        this.appDoctor = appDoctor;
        this.patient = patient;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isAppStatusTaken() {
        return appStatusTaken;
    }

    public void setAppStatusTaken(boolean appStatusTaken) {
        this.appStatusTaken = appStatusTaken;
    }

    public User getAppDoctor() {
        return appDoctor;
    }

    public void setAppDoctor(User appDoctor) {
        this.appDoctor = appDoctor;
    }





    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }


}
