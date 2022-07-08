package com.example.mobileactionlastproject.inf.enums;

/**
 * @author Muhammet Abdullah Koç
 * @since 1.0
 */

public enum EnumCity {

    London ("London"),
    Barcelona ("Barcelona"),
    Ankara ("Ankara"),
    Tokyo ("Tokyo"),
    Mumbai ("Mumbai"),
    ;


    private String city;

    EnumCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return city;
    }
}
