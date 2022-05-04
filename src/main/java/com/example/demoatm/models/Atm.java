package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import com.example.demoatm.models.enums.BankNote;
import com.example.demoatm.models.enums.Currencies;
import com.example.demoatm.models.enums.TypeOFCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Atm extends Info{

    @ManyToOne
    private Bank brand;

    @OneToOne
    private Addresses address;

    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private List<TypeOFCard> typeOFCardEnums;

    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private Set<Currencies> currenciesList;

    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private List<BankNote> bankNoteEnumsList;

    @Column(nullable = false)
    private Double balance = 50000000.0;//so`m

    @OneToOne
    private Users admin;

}
