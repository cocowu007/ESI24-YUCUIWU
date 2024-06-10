package com.esi.appointmentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esi.appointmentservice.dto.AppointmentDto;
import com.esi.appointmentservice.model.Appointment;
import com.esi.appointmentservice.repository.AppointmentRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AppointmentService {

  @Autowired
  private AppointmentRepository appointmentRepository;

  public void appointmentBooked(AppointmentDto appointmentDto) {

    Appointment appointment = mapToAppointment(appointmentDto);
    appointmentRepository.save(appointment);
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
