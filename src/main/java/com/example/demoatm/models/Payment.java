package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment extends Info {

    @ManyToOne
    private Atm atm;

    @Column(nullable = false)
    private Long fromCardNumber;

    @Column(nullable = false)
    private Long toCardNumber;


    @Column(nullable = false)
    private Double amount;


    @OneToOne
    private Order order;
}
