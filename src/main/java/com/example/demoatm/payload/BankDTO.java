package com.example.demoatm.payload;

import com.example.demoatm.abstacts.InfoAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BankDTO extends InfoAddressDTO {
    @NotBlank(message = "Name mustn't be empty")
    private String name;

    private Long addressid;

    private Long bossId;


    public BankDTO(Long addressid) {
        this.addressid = addressid;
    }

}
