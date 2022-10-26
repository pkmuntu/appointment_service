package com.muntu.entrypoint.controller;

import com.muntu.constants.Constants;
import com.muntu.entrypoint.dto.request.AppointmentRequestDTO;
import com.muntu.entrypoint.dto.response.AppointmentResponseDTO;
import com.muntu.rest.RestAPICode;
import com.muntu.rest.RestResponse;
import com.muntu.service.AppointmentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService=appointmentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<AppointmentResponseDTO> createAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
      AppointmentResponseDTO appointmentResponseDTO=appointmentService.saveAppointment(appointmentRequestDTO);
      return RestResponse.of(appointmentResponseDTO, Constants.APPOINTMENT_CREATED, RestAPICode.CREATED_STATUS_CODE);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse<AppointmentResponseDTO> updateAppointment(@Valid @RequestBody AppointmentRequestDTO appointmentRequestDTO){
        AppointmentResponseDTO appointmentResponseDTO=appointmentService.updateAppointment(appointmentRequestDTO);
        return RestResponse.of(appointmentResponseDTO, Constants.APPOINTMENT_UPDATED, RestAPICode.CREATED_STATUS_CODE);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppointmentResponseDTO> getAppointment(@PathVariable Integer id){
        AppointmentResponseDTO appointmentResponseDTO=appointmentService.getAppointment(id);
        return RestResponse.of(appointmentResponseDTO, Constants.APPOINTMENT_DATA, RestAPICode.OK_STATUS_CODE);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<AppointmentResponseDTO> deleteAppointment(@PathVariable Integer id){
        AppointmentResponseDTO appointmentResponseDTO=appointmentService.deleteAppointment(id);
        return RestResponse.of(appointmentResponseDTO, Constants.APPOINTMENT_DELETED, RestAPICode.OK_STATUS_CODE);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RestResponse<List<AppointmentResponseDTO>> getAppointments(@RequestParam(required = true) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate startDate, @RequestParam(required = true)@DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate endDate){
        List<AppointmentResponseDTO> appointmentResponseDTOS=appointmentService.getAppointments(startDate,endDate);
        return RestResponse.of(appointmentResponseDTOS, Constants.APPOINTMENT_LIST, RestAPICode.OK_STATUS_CODE);
    }

}
