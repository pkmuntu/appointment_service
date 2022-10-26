package com.muntu.entrypoint.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentResponseDTO implements Serializable {

    private Integer id;
    private LocalDate date;
    private LocalTime startTime;
    private Integer minute;
    private String purpose;
}
