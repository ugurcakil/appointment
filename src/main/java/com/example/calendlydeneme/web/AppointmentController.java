package com.example.calendlydeneme.web;

import com.example.calendlydeneme.entities.Appointment;
import com.example.calendlydeneme.repositories.AppointmentRepository;
import com.example.calendlydeneme.services.AppointmentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AppointmentController {
    @Autowired
    private AppointmentServiceImpl appService;

    @Autowired
    AppointmentRepository appRepo;

    @GetMapping("/appointments")
    public String listAppointments(Model model){
        model.addAttribute("appointments",appService.listAllAppointments());
        return "appointments";
    }

    @GetMapping("/deleteAppointment")
    public String deleteAppointment(@RequestParam Long appoId){
        appRepo.deleteById(appoId);
        return "redirect:/appointments";
    }

    @GetMapping("/appointments/edit/{id}")
    public String editAppointment(@PathVariable("id") Long id, Model model){
        Appointment appointment= appService.get(id);
        return "redirect:/appointments";
    }
}
