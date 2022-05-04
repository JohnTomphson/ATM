package com.example.demoatm.models;

import com.example.demoatm.abstacts.Time;
import com.example.demoatm.models.enums.TypeOFCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card  extends Time {
    @Column(nullable = false)
    private Integer password = 1234;

    @Column(nullable = false, unique = true)
    private Long cardNumber;

    @Enumerated(EnumType.STRING)
    private TypeOFCard typeOFCardEnums;

    @Column(nullable = false)
    private Double balance = 50000.0;
    @ManyToOne
    private Users owner;

    @ManyToOne
    private Bank bank;


}
