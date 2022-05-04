package com.example.demoatm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface UserPro {
    String getId();
    String getfirstName();
    String getmiddleName();
    String getlastName();
    String getName();
    String getactive();

    @Value("#{target.address.name + ' '  + target.address.placeId}")
    String getAddress();

}
