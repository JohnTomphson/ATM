package com.example.demoatm.models;

import com.example.demoatm.abstacts.Time;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Invoice extends Time {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private boolean active = true;

    @OneToOne
    private Users users;


    @Column(nullable = false)
    private Double amount;

    @OneToOne
    private Payment payment;

}
