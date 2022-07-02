package com.example.mobileactionlastproject.inf.service.entityservice;

import com.example.mobileactionlastproject.gen.service.BaseEntityService;
import com.example.mobileactionlastproject.inf.dao.InfInformationDao;
import com.example.mobileactionlastproject.inf.entity.InfInformation;
import com.example.mobileactionlastproject.inf.enums.EnumCity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class InfInformationEntityService extends BaseEntityService<InfInformation, InfInformationDao> {
    public InfInformationEntityService(InfInformationDao dao) {
        super(dao);
    }

    public InfInformation findByCityAndLocalDate(EnumCity city, LocalDate localDate) {
        return getDao().findByCityAndLocalDate(city, localDate);
    }

    @Transactional
    public void deleteByCityAndLocalDate(EnumCity city, LocalDate localDate) {
        getDao().deleteByCityAndLocalDate(city, localDate);
    }

}
