package com.example.mobileactionlastproject.inf.dao;

import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

public interface InfInformationDao extends JpaRepository<InfInformation, Long> {

    InfInformation findByCityAndLocalDate(EnumCity city, LocalDate date);

    void deleteAllByCityAndLocalDateBetween(EnumCity city, LocalDate startDate, LocalDate endDate);

}
