package com.example.demoatm.models;

import com.example.demoatm.abstacts.Info;
import com.example.demoatm.models.enums.BankNote;
import com.example.demoatm.models.enums.Currencies;
import com.example.demoatm.models.enums.TypeOFCard;
import com.example.demoatm.payload.enumLists.Collections;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Bank extends Info {
    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private List<TypeOFCard> typeOFCard;

    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private Set<Currencies>  currencies;

    @ElementCollection()
    @Enumerated(EnumType.STRING)
    private List<BankNote> bankNote;

    @OneToOne
    private Addresses addresses;

    @OneToOne
    private Users boss;

    @Column(nullable = false)
    private Double balance = 500000000.0; //so`m

    }
