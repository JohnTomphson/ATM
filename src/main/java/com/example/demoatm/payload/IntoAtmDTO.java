package com.example.demoatm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class IntoAtmDTO {
    private String name;
    private Long cardNumber;
    private String brandName;
    private String password;
    private Date due;

}
