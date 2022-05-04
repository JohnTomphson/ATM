package com.example.demoatm.service;

import com.example.demoatm.models.Addresses;
import com.example.demoatm.models.Bank;
import com.example.demoatm.models.Users;
import com.example.demoatm.payload.ApiResponse;
import com.example.demoatm.payload.BankDTO;
import com.example.demoatm.payload.enumLists.Collections;
import com.example.demoatm.projections.BankPro;
import com.example.demoatm.repository.AddressRepo;
import com.example.demoatm.repository.BankRepo;
import com.example.demoatm.repository.UserRepo;
import com.example.demoatm.service.template.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankService implements Crud {
    final BankRepo bankRepo;
    final UserRepo userRepo;
    final AddressRepo addressRepo;
    final Collections collections;



    /**
     * Method that gets all Banks which are active true
     *
     * @return ApiResponse
     */
    @Override
    public ApiResponse getAll() {
        List<BankPro> bankList = bankRepo.findAllByActiveTrue();
        return bankList != null ? new ApiResponse(true, "Success", bankList)
                : new ApiResponse(false, "Failed");
    }



    /**
     * Method that gets Bank by ID
     *
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse getById(Long id) {
        Optional<Bank> bankOptional = bankRepo.findById(id);
        return bankOptional.map(orders -> new ApiResponse(true, "Success", orders))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }




    /**
     * Method which adds new Bank and Address
     *
     * @param bankDTO
     * @return ApiResponse
     */
    public ApiResponse add(BankDTO bankDTO) {
        Optional<Users> usersOptional = userRepo.findById(bankDTO.getBossId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().message("User isn't present").success(false).build();
        Users boss = usersOptional.get();
        Addresses addresses = new Addresses();
        addresses.setName(bankDTO.getDistrictName());
        addresses.setZip_code(bankDTO.getZip_code());
        Addresses save = addressRepo.save(addresses);

        Bank bank = new Bank();
        bank.setName(bankDTO.getName());
        bank.setBoss(boss);
        bank.setAddresses(save);
        bank.setBankNote(collections.bankNoteList());
        bank.setCurrencies(collections.currenciesSet());
        bank.setTypeOFCard(collections.typeOFCards());
        bankRepo.save(bank);
        return new ApiResponse(true, "Saved");
    }

    /**
     * Method which edits Bank:
     * 1) Address District name,
     * 2) Old boss,
     * 3) Bank name;
     *
     * @param id
     * @param newBank
     * @return ApiReponse
     */
    public ApiResponse update(Long id, BankDTO newBank) {
        Optional<Bank> bankOptional = bankRepo.findById(id);
        if (!bankOptional.isPresent())
            return ApiResponse.builder().message("This Bank isn't present").success(false).build();
        Bank oldBank = bankOptional.get();

        Optional<Users> usersOptional = userRepo.findById(newBank.getBossId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().message("User isn't present").success(false).build();
        Users oldBoss = usersOptional.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(newBank.getAddressid());
        if (!addressesOptional.isPresent() && !addressesOptional.isEmpty())
            return ApiResponse.builder().message("This Address isn't present or already exsit").success(false).build();
        Addresses oldAddresses = addressesOptional.get();

        oldAddresses.setName(newBank.getDistrictName());
        Addresses save = addressRepo.save(oldAddresses);

        oldBank.setAddresses(save);
        oldBank.setBoss(oldBoss);
        oldBank.setName(newBank.getName());
        bankRepo.save(oldBank);

        return new ApiResponse(true, "updated");
    }

    /**
     * Method which edits Bank active 'true' to 'false' Bank by ID:
     * 1) Bank
     * 2) Boss
     * as well as 'Address';
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse delete(Long id) {
        Optional<Bank> bankOptional = bankRepo.findById(id);
        if (!bankOptional.isPresent())
            return ApiResponse.builder().message("This Bank isn't present").success(false).build();
        Bank oldBank = bankOptional.get();

        Optional<Users> usersOptional = userRepo.findById(oldBank.getBoss().getId());
        Users oldBoss = usersOptional.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(oldBank.getAddresses().getId());
        Addresses oldAddress = addressesOptional.get();

        oldBank.setActive(false);
        oldBoss.setActive(false);
        oldAddress.setActive(false);

        bankRepo.save(oldBank);
        userRepo.save(oldBoss);
        addressRepo.save(oldAddress);
        return new ApiResponse(true, "Deleted");
    }


}
