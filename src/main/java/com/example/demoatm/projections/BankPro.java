package com.example.demoatm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface BankPro {
    String getId();
    String getName();
    String active();

    @Value("#{target.boss.name + ' '  + target.boss.firstName + ' ' + target.boss.middleName + ' ' + target.boss.active}")
    String getBossName();
    @Value("#{target.address.name + ' '  + target.address.placeId}")
    String getAddressName();

}

