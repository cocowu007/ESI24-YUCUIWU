package com.esi.drservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

}
