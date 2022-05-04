package com.example.demoatm.models.enums;

import com.google.common.collect.Sets;
import lombok.Getter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
@Getter
@ToString
public  enum  Currencies {
    SUM(Sets.newHashSet(
            BankNote.BESMING, BankNote.ELIKMING,
            BankNote.MING, BankNote.YIGIRMAMING,
            BankNote.UNMING, BankNote.YUZMING)),
    USD(Sets.newHashSet(BankNote.BIR,BankNote.BESH,BankNote.YUZ)),
    BITCOIN(Sets.newHashSet(BankNote.ONECOIN));
    private Set<BankNote> permissions;

    Currencies(Set<BankNote> permissions) {
        this.permissions = permissions;
    }
}
