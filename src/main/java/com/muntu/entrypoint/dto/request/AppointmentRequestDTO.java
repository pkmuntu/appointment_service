package com.muntu.entrypoint.dto.request;

import com.muntu.constants.Constants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentRequestDTO {

    private Integer id;
    @NotEmpty(message = Constants.APPOINTMENT_DATE_REQUIRED)
    private LocalDate date;
    @NotEmpty(message = Constants.APPOINTMENT_TIME_REQUIRED)
    private LocalTime startTime;
    @NotEmpty(message = Constants.APPOINTMENT_MINUTE_REQUIRED)
    private Integer minute;
    @NotEmpty(message = Constants.APPOINTMENT_PURPOSE_REQUIRED)
    private String purpose;

}
