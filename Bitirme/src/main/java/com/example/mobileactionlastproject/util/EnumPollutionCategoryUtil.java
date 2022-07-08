package com.example.mobileactionlastproject.util;

import com.example.mobileactionlastproject.inf.enums.EnumPollutionCategory;

import java.math.BigDecimal;

/**
 * This class includes utility methods for converting from {@link BigDecimal} to {@link EnumPollutionCategory}.
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */
public class EnumPollutionCategoryUtil {

    public static EnumPollutionCategory convertCOToEnum(BigDecimal value) {
        Double doubleValue = value.doubleValue();
        if (doubleValue <= 50.0) {
            return EnumPollutionCategory.Good;
        }
        if (doubleValue <= 100.0) {
            return EnumPollutionCategory.Satisfactory;
        }
        if (doubleValue <= 150.0) {
            return EnumPollutionCategory.Moderate;
        }
        if (doubleValue <= 200.0) {
            return EnumPollutionCategory.Poor;
        }
        if (doubleValue <= 300.0) {
            return EnumPollutionCategory.Severe;
        }
        return EnumPollutionCategory.Hazardous;
    }

    public static EnumPollutionCategory convertO3ToEnum(BigDecimal value) {
        Double doubleValue = value.doubleValue();
        if (doubleValue <= 50.0) {
            return EnumPollutionCategory.Good;
        }
        if (doubleValue <= 100.0) {
            return EnumPollutionCategory.Satisfactory;
        }
        if (doubleValue <= 168.0) {
            return EnumPollutionCategory.Moderate;
        }
        if (doubleValue <= 208.0) {
            return EnumPollutionCategory.Poor;
        }
        if (doubleValue <= 748.0) {
            return EnumPollutionCategory.Severe;
        }
        return EnumPollutionCategory.Hazardous;
    }

    public static EnumPollutionCategory convertSO2ToEnum(BigDecimal value) {
        Double doubleValue = value.doubleValue();
        if (doubleValue <= 40.0) {
            return EnumPollutionCategory.Good;
        }
        if (doubleValue <= 80.0) {
            return EnumPollutionCategory.Satisfactory;
        }
        if (doubleValue <= 380.0) {
            return EnumPollutionCategory.Moderate;
        }
        if (doubleValue <= 800.0) {
            return EnumPollutionCategory.Poor;
        }
        if (doubleValue <= 1600.0) {
            return EnumPollutionCategory.Severe;
        }
        return EnumPollutionCategory.Hazardous;
    }
}
