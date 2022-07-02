package com.example.mobileactionlastproject.inf.entity;

import com.example.mobileactionlastproject.gen.entity.BaseEntity;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "INF_INFORMATION")
@Getter
@Setter
public class InfInformation extends BaseEntity {

    @Id
    @SequenceGenerator(name = "InfInformation", sequenceName = "INF_INFORMATION_ID_SEQ")
    @GeneratedValue(generator = "InfInformation")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "CITY", length = 30)
    private EnumCity city;

    @Column(name = "DATE")
    private LocalDate localDate;

    @Column(name = "CO_POLLUTION", precision = 19, scale = 2)
    private BigDecimal COPollution;

    @Column(name = "O3_POLLUTION", precision = 19, scale = 2)
    private BigDecimal O3Pollution;

    @Column(name = "SO2_POLLUTION", precision = 19, scale = 2)
    private BigDecimal SO2Pollution;


}
