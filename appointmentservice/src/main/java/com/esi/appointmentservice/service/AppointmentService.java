package com.esi.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esi.appointmentservice.dto.AppointmentDto;
import com.esi.appointmentservice.model.Appointment;
import com.esi.appointmentservice.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  private final KafkaTemplate<String, AppointmentDto> jsonKafkaTemplate;

  public void sendJsonToAppointmentTopic(AppointmentDto appointmentDto){
    jsonKafkaTemplate.send("appointmentTopic", appointmentDto);
  }

  public void appointmentBooked(AppointmentDto appointmentDto) {

    Appointment appointment = mapToAppointment(appointmentDto);
    appointmentRepository.save(appointment);

    sendJsonToAppointmentTopic(appointmentDto);
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
