package com.example.demoatm.payload;

import com.example.demoatm.abstacts.InfoAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AtmDTO extends InfoAddressDTO {
    private Long id;
    private Long brandId;
    private Long addressId;

    @Max(100000000)
    @Min(50000000)
    private Double quantiyOFMoney;
    private Long adminId;


}
