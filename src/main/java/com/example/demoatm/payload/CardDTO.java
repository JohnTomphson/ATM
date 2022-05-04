package com.example.demoatm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDTO {
    private Long cardNumber;
    private Long brandId;
    private Long ownerId;
    private Integer password;
    private String typeOFCardNumberID;

    @Size(min = 10000, max = 5000000)
    private Double withDzraw;
    private String payToCardnumber;
}
