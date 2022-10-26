package com.muntu.dao.repository;

import com.muntu.dao.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query("SELECT count(*) FROM Appointment a WHERE a.date=:date and a.startTime <= :startTime AND a.endTime>:startTime")
    public Long findByDateAndTimeBetween(LocalDate date, LocalTime startTime);

    public List<Appointment> findByDateBetween(LocalDate startDate, LocalDate endDate);

}
