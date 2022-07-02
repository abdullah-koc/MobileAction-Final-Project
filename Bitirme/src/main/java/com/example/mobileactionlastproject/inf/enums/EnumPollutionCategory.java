package com.example.mobileactionlastproject.inf.enums;

public enum EnumPollutionCategory {

    Good ("Good"),
    Satisfactory ("Satisfactory"),
    Moderate ("Moderate"),
    Poor ("Poor"),
    Severe ("Severe"),
    Hazardous ("Hazardous")
    ;

    private String category;

    EnumPollutionCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category;
    }
}
