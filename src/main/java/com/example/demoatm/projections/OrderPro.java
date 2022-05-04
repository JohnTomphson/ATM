package com.example.demoatm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface OrderPro extends UserPro{
    String getId();
    String getDate();

    @Value("#{target.boss.name + ' '  + target.boss.firstName + ' ' + target.boss.middleName + ' ' + target.boss.active}")
    String getUserName();

}
