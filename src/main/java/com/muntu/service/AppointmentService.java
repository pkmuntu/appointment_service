package com.muntu.service;

import com.muntu.entrypoint.dto.request.AppointmentRequestDTO;
import com.muntu.entrypoint.dto.response.AppointmentResponseDTO;

import java.time.LocalDate;
import java.util.List;


public interface AppointmentService {

    public AppointmentResponseDTO saveAppointment(AppointmentRequestDTO appointmentRequestDTO);
    public AppointmentResponseDTO updateAppointment(AppointmentRequestDTO appointmentRequestDTO);
    public AppointmentResponseDTO getAppointment(Integer appointmentId);
    public AppointmentResponseDTO deleteAppointment(Integer appointmentId);
    public List<AppointmentResponseDTO> getAppointments(LocalDate startDate, LocalDate endDate);


}
