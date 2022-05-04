package com.example.demoatm.models.enums;

import lombok.*;
@AllArgsConstructor
@Getter
@ToString
public enum AplicationUserPermission {
    CREATE_BANK("create:bank"),CREATE_ATM("create:atm"), CREATE_USER("create:user"),CREATE_CARD("create:card"),
    READ_BANK("read:bank"),READ_ATM("read:atm"),READ_USER("read:user"), READ_CARD("read:card"),
    DELETE_BANK("delete:bank"),DELETE_ATM("delete:atm"),DELETE_USER("delete:user"), DELETE_CARD("delete:card"),
    UPDATE_BANK("update:bank"), UPDATE_ATM("update:atm"), UPDATE_USER("update:user"), UPDATE_CARD("update:card");
    private final String permissions;

}
