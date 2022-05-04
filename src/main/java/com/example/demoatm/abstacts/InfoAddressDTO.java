package com.example.demoatm.abstacts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
@AllArgsConstructor
@NoArgsConstructor
@Data
public abstract class InfoAddressDTO {
    @NotBlank(message = "District name mustn't empty")
    private String districtName;
    @NotBlank(message = "Place id mustn't empty and id must be unique")
    private Integer zip_code;

}
