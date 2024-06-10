package com.esi.patientservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esi.patientservice.dto.PatientDto;
import com.esi.patientservice.model.Patient;
import com.esi.patientservice.repository.PatientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public void addPatientInfo(PatientDto patientDto) {

        Patient patient = mapToPatient(patientDto);
        patientRepository.save(patient);

    }

    private Patient mapToPatient(PatientDto patientDto) {

        return Patient.builder()
                .patientId(patientDto.getPatientId())
                .patientName(patientDto.getPatientName())
                .patientData(patientDto.getPatientData())
                .build();
    }

}
