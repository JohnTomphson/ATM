package com.example.demoatm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface CardPro {
    String getId();
    String getcardNumber();
    String gettypeOfCard();
    String getissue();
    String getdue();

    @Value("#{target.boss.name + ' '  + target.boss.firstName + ' ' + target.boss.middleName + ' ' + target.boss.active}")
    String getOwnerName();

    @Value("#{target.bank.name + ' '  + target.bank.active}")
    String getBank();

}
