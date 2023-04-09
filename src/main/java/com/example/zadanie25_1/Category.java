package com.example.zadanie25_1;

public enum Category {
    HOME_DUTIES("Obowiązki domowe"),
    WORK("Praca"),
    FAMILY("Rodzina"),
    HOBBY("Pasja");

    private String plTranslation;

    public String getPlTranslation() {
        return plTranslation;
    }

    Category(String plTranslation) {
        this.plTranslation = plTranslation;
    }
}
