package com.example.demoatm.projections;

import com.example.demoatm.models.enums.TypeOFCard;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

public interface AtmPro {
    List<TypeOFCard> getTypeOFCardEnums();

    @Value("#{target.bank.name}")
    String brandName();

    @Value("#{target.admin.name + ' '  + target.admin.firstName + ' ' + target.admin.middleName + ' ' + target.admin.active}")
    String getAdminName();

    @Value("#{target.address.name + ' '  + target.address.placeId}")
    String getAddressName();

}
