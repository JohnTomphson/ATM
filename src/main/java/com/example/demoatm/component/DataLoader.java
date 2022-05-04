package com.example.demoatm.component;

import com.example.demoatm.models.*;
import com.example.demoatm.models.enums.AplicationUserPermission;
import com.example.demoatm.models.enums.ApplicationUserRole;
import com.example.demoatm.models.enums.TypeOFCard;
import com.example.demoatm.payload.enumLists.Collections;
import com.example.demoatm.repository.*;
import com.example.demoatm.service.CardService;
import com.example.demoatm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    final Collections collections;
    final UserService userService;
    final UserRepo userRepo;
    final BankRepo bankRepo;
    final CardRepo cardRepo;
    final AddressRepo addressRepo;
    final RoleRepository roleRepository;
    final CardService cardService;
    @Value("${spring.sql.init.mode}")
    String mode;


    @Override
    public void run(String... args) throws Exception {
        /**
         * You should add card before project run.
         */
        if (mode.equals("always")) {

        Role boss = new Role();
        boss.setRoleName(ApplicationUserRole.BOSS);
        Set<AplicationUserPermission> permissions = ApplicationUserRole.BOSS.getPermissions();
        boss.setPermissionEnum(permissions);
        roleRepository.save(boss);
        Role customer = new Role();
        customer.setRoleName(ApplicationUserRole.CUSTOMER);
        Set<AplicationUserPermission> permissionList = ApplicationUserRole.CUSTOMER.getPermissions();
        customer.setPermissionEnum(permissionList);
        roleRepository.save(customer);
            List<Role> roleList = new ArrayList<>(Arrays.asList(customer,boss));
            List<Role> customerList = new ArrayList<>(Arrays.asList(customer));

            Users userService1 = userService.addForDataloader("Beruniy", 1111, "@yashnar11gmail.com",
                    "aaaa", "bbbb", "grfedc", roleList);
            Users users1 = userRepo.save(userService1);

            Addresses save = addressRepo.save(userService.addressesDorDataloader("Toshkent", 1232));

            Bank bank = new Bank();
            bank.setName("Agro Bank");
            bank.setBoss(users1);
            bank.setAddresses(save);
            bank.setBankNote(collections.bankNoteList());
            bank.setCurrencies(collections.currenciesSet());
            bank.setTypeOFCard(collections.typeOFCards());
            bankRepo.save(bank);

            Card card = new Card();
            card.setTypeOFCardEnums(TypeOFCard.HUMO);
            card.setCardNumber(cardService.randomCardNumber12("8600"));
            card.setOwner(users1);
            card.setBank(bank);
            cardRepo.save(card);

        }
    }
}
