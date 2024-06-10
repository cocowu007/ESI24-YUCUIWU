package com.esi.drservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esi.drservice.dto.AppointmentDto;
import com.esi.drservice.service.DrService;


@RestController
@RequestMapping("/api")
public class DrController {

    @Autowired
    private DrService drService;

    
    @GetMapping("/appointment")
    public List<AppointmentDto> getAllAppointments() {
        
        return drService.getAllAppointments();
    }
     

}
