package com.example.demoatm.payload.enumLists;

import com.example.demoatm.models.enums.ApplicationUserRole;
import com.example.demoatm.models.enums.BankNote;
import com.example.demoatm.models.enums.Currencies;
import com.example.demoatm.models.enums.TypeOFCard;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Collections {


    /**
     * Method that gets all BankNoteEnumList
     *
     * Purpose: For DataLoader and some on
     * @return List
     */
    @Bean
    public List<BankNote> bankNoteList() {
        List<BankNote> bankNoteList = new ArrayList<BankNote>(
                Arrays.asList(BankNote.BIR, BankNote.BESMING, BankNote.ELIK, BankNote.YUZ,
                        BankNote.MING, BankNote.BESMING, BankNote.UNMING, BankNote.YIGIRMAMING, BankNote.ELIKMING, BankNote.YUZMING,
                        BankNote.ONECOIN));
        return bankNoteList;
    }



    /**
     * Method that gets all TypeOfCardsEnumList
     *
     * Purpose: For DataLoader and some on
     * @return List
     */
    @Bean
    public List<TypeOFCard> typeOFCards() {
        List<TypeOFCard> typeOFCards = new ArrayList<TypeOFCard>
                (Arrays.asList(TypeOFCard.HUMO, TypeOFCard.UZCARD,
                        TypeOFCard.VISA, TypeOFCard.MASTERCAD));
        return typeOFCards;
    }



    /**
     * Method that gets all CurrenciesEnumList
     *
     * Purpose: For DataLoader and some on
     * @return Set()
     */
    @Bean
    public Set<Currencies> currenciesSet() {
        Set<Currencies> currenciesSet = new LinkedHashSet<Currencies>
                (Arrays.asList(Currencies.SUM, Currencies.BITCOIN, Currencies.USD));
        return currenciesSet;
    }

    /**
     * Method that gets all ApplicationUserRoleEnumList
     *
     * Purpose: For DataLoader and some on
     * @return Set();
     */
    @Bean
    public Set<ApplicationUserRole> applicationUserRoles() {
        Set<ApplicationUserRole> roleList = new HashSet<ApplicationUserRole>(
                Arrays.asList(
                        ApplicationUserRole.BOSS, ApplicationUserRole.CUSTOMER,
                        ApplicationUserRole.THE_BOSS, ApplicationUserRole.ADMIN));
        return roleList;
    }
}
