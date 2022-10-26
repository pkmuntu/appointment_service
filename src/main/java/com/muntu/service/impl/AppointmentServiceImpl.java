package com.muntu.service.impl;

import com.muntu.constants.Constants;
import com.muntu.dao.entity.Appointment;
import com.muntu.dao.repository.AppointmentRepository;
import com.muntu.entrypoint.dto.request.AppointmentRequestDTO;
import com.muntu.entrypoint.dto.response.AppointmentResponseDTO;
import com.muntu.exception.AppointmentNotFoundException;
import com.muntu.exception.TimeNotAvailableException;
import com.muntu.service.AppointmentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository){
        this.appointmentRepository=appointmentRepository;
    }

    @Override
    public AppointmentResponseDTO saveAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        validateAppointmentRequestDTO(appointmentRequestDTO);
        Appointment appointment=getAppointment(appointmentRequestDTO);
        appointment= appointmentRepository.save(appointment);
        return copyPropertiesFromAppointmentToAppointmentResponseDTO(appointment);
    }

    @Override
    public AppointmentResponseDTO updateAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        validateAppointmentRequestDTO(appointmentRequestDTO);
        Appointment appointment=getAppointmentById(appointmentRequestDTO.getId());
        CopyPropertiesFromAppointmentRequestDTOToAppointment(appointmentRequestDTO,appointment);
        appointmentRepository.save(appointment);
        return copyPropertiesFromAppointmentToAppointmentResponseDTO(appointment);
    }

    @Override
    public AppointmentResponseDTO getAppointment(Integer appointmentId) {
        Appointment appointment=getAppointmentById(appointmentId);
        return copyPropertiesFromAppointmentToAppointmentResponseDTO(appointment);
    }

    @Override
    public AppointmentResponseDTO deleteAppointment(Integer appointmentId) {
        Appointment appointment=getAppointmentById(appointmentId);
        appointmentRepository.delete(appointment);
        return copyPropertiesFromAppointmentToAppointmentResponseDTO(appointment);
    }

    @Override
    public List<AppointmentResponseDTO> getAppointments(LocalDate startDate, LocalDate endDate) {
        List<Appointment> appointments= appointmentRepository.findByDateBetween(startDate,endDate);
        List<AppointmentResponseDTO> appointmentResponseDTOS=new ArrayList<>();
        CopyPropertiesFromAppointmentListToAppointmentResponseDTO(appointments,appointmentResponseDTOS);
        return appointmentResponseDTOS;
    }

    private void CopyPropertiesFromAppointmentListToAppointmentResponseDTO(List<Appointment> appointments, List<AppointmentResponseDTO> appointmentResponseDTOS) {
       appointments.forEach(appointment -> {
           appointmentResponseDTOS.add(copyPropertiesFromAppointmentToAppointmentResponseDTO(appointment));
       });
    }
    private void CopyPropertiesFromAppointmentRequestDTOToAppointment(AppointmentRequestDTO appointmentRequestDTO, Appointment appointment) {
        appointment.setDate(appointmentRequestDTO.getDate());
        appointment.setStartTime(appointmentRequestDTO.getStartTime());
        appointment.setEndTime(appointmentRequestDTO.getStartTime().plusMinutes(appointmentRequestDTO.getMinute()));
        appointment.setMinute(appointmentRequestDTO.getMinute());
        appointment.setPurpose(appointmentRequestDTO.getPurpose());
    }

    private AppointmentResponseDTO copyPropertiesFromAppointmentToAppointmentResponseDTO(Appointment appointment) {
        AppointmentResponseDTO appointmentResponseDTO= new AppointmentResponseDTO();
        BeanUtils.copyProperties(appointment,appointmentResponseDTO);
        return appointmentResponseDTO;
    }

    private void validateAppointmentRequestDTO(AppointmentRequestDTO appointmentRequestDTO) {
        long reocrd=getAppointmentsByDateAndTimeRange(appointmentRequestDTO.getDate(),appointmentRequestDTO.getStartTime());
        if(reocrd!=0)
            throw new TimeNotAvailableException(Constants.APPOINTMENT_DATE_TIME,Constants.APPOINTMENT_DATE_TIME_OCCUPIED);
    }

    private Long getAppointmentsByDateAndTimeRange(LocalDate date, LocalTime startTime){
        return appointmentRepository.findByDateAndTimeBetween(date,startTime);

    }

    private Appointment getAppointment(AppointmentRequestDTO appointmentRequestDTO) {
        return Appointment.of(appointmentRequestDTO.getDate(),appointmentRequestDTO.getStartTime(),appointmentRequestDTO.getStartTime().plusMinutes(appointmentRequestDTO.getMinute()), appointmentRequestDTO.getMinute(),appointmentRequestDTO.getPurpose());
    }

    private Appointment getAppointmentById(Integer appointmentId) {
        Optional<Appointment> appointment=appointmentRepository.findById(appointmentId);
        if(appointment.isPresent())
            return appointment.get();
        throw new AppointmentNotFoundException(Constants.APPOINTMENT_ID,Constants.APPOINTMENT_NOT_FOUND);
    }

}
