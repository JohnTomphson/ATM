package com.example.demoatm.service;

import com.example.demoatm.api.HttpApi;
import com.example.demoatm.models.*;
import com.example.demoatm.models.enums.BankNote;
import com.example.demoatm.payload.ApiResponse;
import com.example.demoatm.payload.AtmDTO;
import com.example.demoatm.payload.CardDTO;
import com.example.demoatm.payload.enumLists.Collections;
import com.example.demoatm.projections.AtmPro;
import com.example.demoatm.repository.*;
import com.example.demoatm.service.template.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class AtmService implements Crud {
    final AtmRepo atmRepo;
    final BankRepo bankRepo;
    final UserRepo userRepo;
    final AddressRepo addressRepo;
    final Collections collections;
    final CardRepo cardRepo;
    final HttpApi api;



    /**
     * Method that gets all ATMs which are active true
     *
     * @return ApiResponse
     */
    @Override
    public ApiResponse getAll() {
        List<AtmPro> atmProList = atmRepo.findAllByActiveTrue();
        return atmProList != null ? new ApiResponse(true, "Success", atmProList)
                : new ApiResponse(false, "Failed");
    }

    /**
     * Method that gets ATM by ID
     *
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse getById(Long id) {
        Optional<Atm> atmOptional = atmRepo.findById(id);
        return atmOptional.map(orders -> new ApiResponse(true, "Success", orders))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }




    /**
     * Method which adds new ATM and Address
     *
     * @param atmDTO
     * @return ApiResponse
     */
    public ApiResponse add(AtmDTO atmDTO) {
        Optional<Bank> bankOptional = bankRepo.findById(atmDTO.getBrandId());
        if (!bankOptional.isPresent())
            return ApiResponse.builder().message("Brand not found").success(false).build();
        Bank bank = new Bank();

        Optional<Users> usersOptional = userRepo.findById(atmDTO.getAdminId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().message("Admin isn't present").success(false).build();
        Users admin = usersOptional.get();

        Addresses addresses = new Addresses();
        addresses.setName(atmDTO.getDistrictName());
        addresses.setZip_code(atmDTO.getZip_code());
        Addresses save = addressRepo.save(addresses);

        Atm newAtm = new Atm();
        newAtm.setBrand(bank);
        newAtm.setAddress(save);
        newAtm.setAdmin(admin);
        newAtm.setTypeOFCardEnums(collections.typeOFCards());
        newAtm.setBankNoteEnumsList(collections.bankNoteList());
        atmRepo.save(newAtm);
        return new ApiResponse(true, "Saved");
    }




    /**
     * Method which edits Bank:
     * 1) Address District name,
     * 2) Old Admin,
     * 3) Brand name;
     * 4) Balance
     * @param id
     * @param newAtm
     * @return ApiResponse
     */
    public ApiResponse update(Long id, AtmDTO newAtm) {
        Optional<Bank> bankOptional = bankRepo.findById(newAtm.getBrandId());
        if (!bankOptional.isPresent())
            return ApiResponse.builder().message("Brand isn't present").success(false).build();
        Bank oldbrend = bankOptional.get();

        Optional<Atm> optionalAtm = atmRepo.findById(id);
        if (!optionalAtm.isPresent())
            return ApiResponse.builder().message("This Atm isn't present").success(false).build();
        Atm oldAtm = optionalAtm.get();

        Optional<Users> usersOptional = userRepo.findById(newAtm.getAdminId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().message("Admin isn't present").success(false).build();
        Users oldAdmin = usersOptional.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(newAtm.getAddressId());
        if (!addressesOptional.isPresent() && !addressesOptional.isEmpty())
            return ApiResponse.builder().message("This Address isn't present or already exsit").success(false).build();
        Addresses oldAddresses = addressesOptional.get();

        oldAddresses.setName(newAtm.getDistrictName());
        Addresses save = addressRepo.save(oldAddresses);

        oldAtm.setAddress(save);
        oldAtm.setAdmin(oldAdmin);
        oldAtm.setBrand(oldbrend);
        oldAtm.setBalance(newAtm.getQuantiyOFMoney());
        atmRepo.save(oldAtm);
        return new ApiResponse(true, "updated");
    }


    /**
     * Method which edits ATM active 'true' to 'false' ATM by ID:
     * 1) ATM
     * 2) Boss
     * 3) ADDRESS
     * as well as 'Address';
     * @param id
     * @return ApiResponse
     */
    @Override
    public ApiResponse delete(Long id) {
        Optional<Atm> optionalAtm = atmRepo.findById(id);
        if (!optionalAtm.isPresent())
            return ApiResponse.builder().message("This Atm isn't present").success(false).build();
        Atm oldAtm = optionalAtm.get();

        Optional<Bank> bankOptional = bankRepo.findById(oldAtm.getBrand().getId());
        if (!bankOptional.isPresent())
            return ApiResponse.builder().message("Brand isn't present").success(false).build();
        Bank oldbrend = bankOptional.get();

        Optional<Users> usersOptional = userRepo.findById(oldAtm.getAdmin().getId());
        Users oldBoss = usersOptional.get();

        Optional<Addresses> addressesOptional = addressRepo.findById(oldAtm.getAddress().getId());
        Addresses oldAddress = addressesOptional.get();

        oldAtm.setActive(false);
        oldBoss.setActive(false);
        oldAddress.setActive(false);

        atmRepo.save(oldAtm);
        userRepo.save(oldBoss);
        addressRepo.save(oldAddress);
        return new ApiResponse(true, "Deleted");
    }

    /**
     * !!!!!!!!!!!!You will  fix Atm withdraw!!!!!!!!!!!!!1
     * Actions command numbers list
     * 1) get Balance  +
     * 2) setPassword  +
     * 3) withDraw / 2.1) quantity
     * 4) payToCard / 4.1) quantity
     * 5)
     *
     * @param cardNumber
     * @param password
     * @param actionId
     * @return
     */
    public ApiResponse signUp(Long cardNumber, Integer password, Integer actionId) {
        Optional<Card> cardOptional = cardRepo.findCardByCardNumber(cardNumber);

        if (!cardOptional.isPresent())
            return ApiResponse.builder().success(false).message("Card not found").build();

        if (!password.equals(cardOptional.get().getPassword()))
            return ApiResponse.builder().success(false).message("Password error").build();

        Double balance = 0.0;
        if (actionId.equals(1))
            cardOptional.get().getBalance();
        else
            return ApiResponse.builder().success(false).message("Action is not present").build();

        return new ApiResponse(true, "Balance", balance);
    }

    public ApiResponse updatePasswordWhichUserCard(Long cardNumber, Integer password, Integer actionId, CardDTO cardDTO) {
        Optional<Card> cardOptional = cardRepo.findCardByCardNumber(cardNumber);
        if (!cardOptional.isPresent())
            return ApiResponse.builder().success(false).message("Card not found").build();

        if (!password.equals(cardOptional.get().getPassword()))
            return ApiResponse.builder().success(false).message("Password error").build();

        Card card = cardOptional.get();
        if (actionId.equals(2))
            card.setPassword(cardDTO.getPassword());
        else
            return ApiResponse.builder().success(false).message("Action is not present").build();
        cardRepo.save(card);

        return new ApiResponse(false, "Password updated");
    }

//    public ApiResponse withDrawMoney(Long cardNumber, Integer password,
//                                     Integer actionId,Double currencyBankNoteId,
//                                     CardDTO cardDTO,Long atmId) {
//
//        Optional<Atm> atmOptional = atmRepo.findById(atmId);
//        if (!atmOptional.isPresent())
//            return ApiResponse.builder().message("Atm Not found").success(false).build();
//
//        Atm atm = atmOptional.get();
//        Optional<Card> cardOptional = cardRepo.findCardByCardNumber(cardNumber);
//        if (!cardOptional.isPresent())
//            return ApiResponse.builder().success(false).message("Card not found").build();
//
//        if (!password.equals(cardOptional.get().getPassword()))
//            return ApiResponse.builder().success(false).message("Password error").build();
//
//
//
//        Card card = cardOptional.get();
//        if (actionId.equals(3)) {
//            Double balance = card.getBalance();
//            Double usdBalance = balance/HttpApi.getOneUsdCurrencyRate();
//            Double withDraw = cardDTO.getWithDzraw();
//
//            /**
//             * Proporsiya:
//             *            find persent:
//             *                        formul:  x / 100 = z
//             *                        z = One persent of x
//             *                        Find persent  = z * y;
//             */
//
//            Double OnePer_sent = withDraw;
//            Double generalWithdraw = withDraw + OnePer_sent;
//
//
//            if (generalWithdraw > balance)
//                return ApiResponse.builder().success(false).
//                        message("Your Balance has not enough money!" +
//                        "You should withdraw money little than old ").build();
//
//            if (balance > generalWithdraw){
//
//                Double quantity = balance - generalWithdraw;
//                Double atmBalance = (atm.getBalance() - withDraw)+OnePer_sent;
//
//
//
//                Double a = withDraw;
//                int size = 50000000;  // 50 mln
//                if (a >= 5000.0 && a <= 500000.0) {
//
//                    //
////            int b = a / 100;
//                    int b = a / 100000;
//                    switch (b) {
//                        case 1:
//                            printer(b, "1 yuz ming");
//                            break;
//                        case 2:
//                            printer(b, "1 yuz ming");
//                            break;
//                        case 3:
//                            printer(b, "1 yuz ming");
//                            break;
//                        case 4:
//                            printer(b, "1 yuz ming");
//                            break;
//                        case 5:
//                            printer(b, "1 yuz ming");
//                            break;
//                        default:
//                            System.out.print(" ");
//                    }
////            int c = (a % 100) / 10;
//                    double c = (a % 100000.0) / 10000.0;
//
//                    switch (c) {
//                        case 1:
//                            printer(c, "10 ming");
//                            break;
//                        case 2:
//                            printer(c, "20 ming");
//                            break;
//                        case 3:
//                            printer(c, " 10 ming");
//                            break;
//                        case 4:
//                            printer(c / 2, " 20 ming");
//                            break;
//                        case 5:
////                    System.out.print(" Ellik ming");
//                            printer(c, "10 ming");
//                            break;
//                        case 6:
//                            printer(c/2, " 20 ming");
//                            break;
//                        case 7:
//                            printer(c, " 10 ming");
//                            break;
//                        case 8:
//                            printer(c/2,"20 ming");
//                            break;
//                        case 9:
//                            printer(c,"10 ming");
//                            break;
//                        default:
//                            System.out.print(" ");
//                    }
////            int d = (a % 100) % 10;
//                    int d = (a % 10000) / 1000;
//                    switch (d) {
//                        case 5:
//                            System.out.print(" Besh ming");
////                    System.out.println(" 1ta besh ming");
//                            break;
//                        default:
//                            System.out.print(" ");
//                    }
//                    System.out.println();
//                }
//
//            }
//        }
//
//
//    }

    public static void printer(double nechtaChiqarishKerak, String nomi) {
        for (int i = 1; i <= nechtaChiqarishKerak; i++) {
            System.out.println(nomi);
        }
    }

}