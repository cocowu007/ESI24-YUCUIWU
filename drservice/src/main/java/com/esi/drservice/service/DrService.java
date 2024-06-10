package com.esi.drservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.esi.drservice.repository.DrRepository;
import com.esi.drservice.model.Appointment;
import com.esi.drservice.dto.AppointmentDto;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.annotation.KafkaListener;

@RequiredArgsConstructor
@Service
public class DrService {

    @Autowired
    private DrRepository drRepository;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @KafkaListener(topics = "appointmentTopic", groupId = "appointEventGroup" )
    public void processAppointment(AppointmentDto appointmentDto) {

        Appointment appointment = mapToAppointment(appointmentDto);
        drRepository.save(appointment);        

    }

    private Appointment mapToAppointment(AppointmentDto appointmentDto) {

        return Appointment.builder()
                .appointmentid(appointmentDto.getAppointmentid())
                .patientId(appointmentDto.getPatientId())
                .patientName(appointmentDto.getPatientName())
                .drId(appointmentDto.getDrId())
                .date(appointmentDto.getDate())
                .build();
    }

    private AppointmentDto mapToAppointmentDto(Appointment appointment) {

        return AppointmentDto.builder()
                .appointmentid(appointment.getAppointmentid())
                .patientId(appointment.getPatientId())
                .patientName(appointment.getPatientName())
                .drId(appointment.getDrId())
                .date(appointment.getDate())
                .build();
    }

    public   List<AppointmentDto> getAllAppointments(){
        List<Appointment> appointments =  new ArrayList<>();
        drRepository.findAll().forEach(appointments::add);
        return appointments.stream().map(this::mapToAppointmentDto).toList();
    }

}
