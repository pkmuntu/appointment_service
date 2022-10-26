package com.muntu.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.muntu.constants.Constants;
import com.muntu.exception.CustomValidationFailureException;
import com.muntu.utils.ValidatorUtil;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Appointment")
@Entity
public class Appointment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NonNull
    @Column(name = "appointment_date")
    private LocalDate date;
    @NonNull
    @Column(name = "appointment_start_time")
    private LocalTime startTime;
    @NonNull
    @Column(name = "appointment_end_time")
    private LocalTime endTime;
    @NonNull
    @Column(name = "appointment_minute")
    private Integer minute;
    @NonNull
    @Column(name = "appointment_purpose")
    private String purpose;

    public static Appointment of(LocalDate date,LocalTime startTime, LocalTime endTime, Integer minute,String purpose){
        if(ValidatorUtil.isNull(date))
            throw new CustomValidationFailureException(Constants.APPOINTMENT_DATE,Constants.APPOINTMENT_DATE_REQUIRED);
        if(ValidatorUtil.isNull(startTime))
            throw new CustomValidationFailureException(Constants.APPOINTMENT_TIME,Constants.APPOINTMENT_TIME_REQUIRED);
        if(ValidatorUtil.isNull(endTime))
            throw new CustomValidationFailureException(Constants.APPOINTMENT_TIME,Constants.APPOINTMENT_TIME_REQUIRED);
        if(ValidatorUtil.isNull(minute))
            throw new CustomValidationFailureException(Constants.APPOINTMENT_MINUTE,Constants.APPOINTMENT_MINUTE_REQUIRED);
        if(ValidatorUtil.isNull(purpose))
            throw new CustomValidationFailureException(Constants.APPOINTMENT_PURPOSE,Constants.APPOINTMENT_PURPOSE_REQUIRED);
        return new Appointment(date,startTime,endTime,minute,purpose);
    }

}
