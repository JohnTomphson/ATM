package com.example.demoatm.models.enums;

public enum BankNote {
    MING("1000"), BESMING("5000"), UNMING("10000"), YIGIRMAMING("20000"),
    ELIKMING("50000"), YUZMING("100000"),
    BIR("1"), BESH("5"), ELIK("50"), YUZ("100"),
    ONECOIN("00.2");
    private final String bankNotes;

    BankNote(String bankNotes) {
        this.bankNotes = bankNotes;
    }
}
