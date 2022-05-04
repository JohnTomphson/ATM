package com.example.demoatm.payload;

import com.example.demoatm.abstacts.InfoAddressDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO extends InfoAddressDTO {
    @Size(min = 5,max = 10)
    private String userName, password;

    @Size(min = 3,max = 7)
    private String firstName, middleName, lastName;

    @Size(min = 4,max = 4)
    private Integer zip_code;
}
