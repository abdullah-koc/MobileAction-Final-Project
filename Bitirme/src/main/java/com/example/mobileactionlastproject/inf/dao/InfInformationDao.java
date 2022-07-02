package com.example.mobileactionlastproject.inf.dao;

import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface InfInformationDao extends JpaRepository<InfInformation, Long> {

    InfInformation findByCityAndLocalDate(EnumCity city, LocalDate date);

    void deleteByCityAndLocalDate(EnumCity city, LocalDate date);

}
