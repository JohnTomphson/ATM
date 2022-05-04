package com.example.demoatm.models.enums;

import java.util.Set;

public enum TypeOFCard {
    HUMO("8600"),UZCARD("9860"),
    VISA("4000"),MASTERCAD("5241");
    private final String supportedOfCard;

    TypeOFCard(String supportedOfCard) {
        this.supportedOfCard = supportedOfCard;
    }


    public String getSupportedOfCard() {
        return supportedOfCard;
    }
}
