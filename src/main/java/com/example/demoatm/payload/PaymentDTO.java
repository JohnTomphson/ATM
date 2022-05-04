package com.example.demoatm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
private Long brandId;

private Long fromCardNumber;
private Long toCardNumber;

@Size(min = 10000, max = 100000000)
private Double amountOfMoney;

private Long invoiceId;
}
