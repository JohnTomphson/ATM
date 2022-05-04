package com.example.demoatm.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InvoiceDTO {
private Long orderId;
private Double amount;
private Date due;
}

