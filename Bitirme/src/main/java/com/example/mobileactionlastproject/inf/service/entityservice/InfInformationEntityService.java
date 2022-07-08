package com.example.mobileactionlastproject.inf.service.entityservice;

import com.example.mobileactionlastproject.gen.service.BaseEntityService;
import com.example.mobileactionlastproject.inf.dao.InfInformationDao;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

@Service
public class InfInformationEntityService extends BaseEntityService<InfInformation, InfInformationDao> {
    public InfInformationEntityService(InfInformationDao dao) {
        super(dao);
    }

    public InfInformation findByCityAndLocalDate(EnumCity city, LocalDate localDate) {
        return getDao().findByCityAndLocalDate(city, localDate);
    }

    @Transactional
    public void deleteAllByCityAndLocalDateBetween(EnumCity city, LocalDate startDate, LocalDate endDate) {
        getDao().deleteAllByCityAndLocalDateBetween(city, startDate, endDate);
    }


}
