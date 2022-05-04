package com.example.demoatm.service;

import com.example.demoatm.models.Bank;
import com.example.demoatm.models.Card;
import com.example.demoatm.models.Users;
import com.example.demoatm.models.enums.TypeOFCard;
import com.example.demoatm.payload.ApiResponse;
import com.example.demoatm.payload.CardDTO;
import com.example.demoatm.projections.CardPro;
import com.example.demoatm.repository.BankRepo;
import com.example.demoatm.repository.CardRepo;
import com.example.demoatm.repository.UserRepo;
import com.example.demoatm.service.template.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService implements Crud {
    final BankRepo bankRepo;
    final UserRepo userRepo;
    final CardRepo cardRepo;

    /**
     * Method that gets all Cards which are active true
     *
     * @return ApiResponse
     */
    @Override
    public ApiResponse getAll() {
        List<CardPro> cardProList = cardRepo.findAllByActiveTrue();
        return cardProList != null ? new ApiResponse(true, "Success", cardProList)
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
        Optional<Card> card = cardRepo.findById(id);
        return card.map(card1 -> new ApiResponse(true, "Success", card1))
                .orElseGet(() -> new ApiResponse(false, "Not Found"));
    }




    /**
     * Method which adds new Card;
     *
     * @param cardDTO
     * @return ApiResponse
     */
    public ApiResponse add(CardDTO cardDTO) {
        Optional<Bank> bankOptional = bankRepo.findById(cardDTO.getBrandId());
        if (bankOptional.isEmpty())
            return ApiResponse.builder().success(false).message("BankID Not found").build();
        Bank newbank = bankOptional.get();

        Optional<Card> card = cardRepo.findCardByCardNumber(cardDTO.getCardNumber());
        if (card.isPresent())
            return ApiResponse.builder().success(false).message("This card already exsited").build();

        Card newCard = new Card();

        Optional<Users> usersOptional = userRepo.findById(cardDTO.getOwnerId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().success(false).message("This user is not present").build();

        Users users = usersOptional.get();
        Long originalCardNumber;
        if (cardDTO.getTypeOFCardNumberID().equals("1")) {
            newCard.setTypeOFCardEnums(TypeOFCard.HUMO);
            originalCardNumber = randomCardNumber12("9860");
        } else if (cardDTO.getTypeOFCardNumberID().equals("2")) {
            newCard.setTypeOFCardEnums(TypeOFCard.UZCARD);
            originalCardNumber = randomCardNumber12("8600");
        } else if (cardDTO.getTypeOFCardNumberID().equals("3")) {
            newCard.setTypeOFCardEnums(TypeOFCard.VISA);
            originalCardNumber = randomCardNumber12("4000");
        } else if (cardDTO.getTypeOFCardNumberID().equals("4")) {
            newCard.setTypeOFCardEnums(TypeOFCard.MASTERCAD);
            originalCardNumber = randomCardNumber12("5241");
        } else return ApiResponse.builder().success(false).message("This card is not present").build();


        newCard.setOwner(users);
        newCard.setBank(newbank);
        newCard.setCardNumber(originalCardNumber);
        cardRepo.save(newCard);
        return new ApiResponse(true, "Saved");
    }




    /**
     * Method which edits only 'Password:
     *
     * @param id
     * @param cardDTO
     * @return ApiReponse
     */
    public ApiResponse update(Long id, CardDTO cardDTO) {
        Optional<Bank> bankOptional = bankRepo.findById(cardDTO.getBrandId());
        if (bankOptional.isEmpty())
            return ApiResponse.builder().success(false).message("BankID Not found").build();
        Optional<Card> cardOptional = cardRepo.findCardByCardNumber(cardDTO.getCardNumber());
        if (!cardOptional.isPresent())
            return ApiResponse.builder().success(false).message("This card is not present").build();
        Card card = cardOptional.get();

        Optional<Users> usersOptional = userRepo.findById(cardDTO.getOwnerId());
        if (!usersOptional.isPresent())
            return ApiResponse.builder().success(false).message("This user is not present").build();


        String s = cardDTO.getCardNumber().toString();
        if (s.startsWith(TypeOFCard.HUMO.getSupportedOfCard()))
            card.setPassword(cardDTO.getPassword());
        else if (s.startsWith(TypeOFCard.UZCARD.getSupportedOfCard()))
            card.setPassword(cardDTO.getPassword());
        else if (s.startsWith(TypeOFCard.VISA.getSupportedOfCard()))
            card.setPassword(cardDTO.getPassword());
        else if (s.startsWith(TypeOFCard.MASTERCAD.getSupportedOfCard()))
            card.setPassword(cardDTO.getPassword());

        cardRepo.save(card);
        return new ApiResponse(true, "Updated");
    }

    /**
     * Method which edits
     * card active 'true' to 'false' Bank by ID:
     * @param cardNumber
     * @return
     */
    @Override
    public ApiResponse delete(Long cardNumber) {
        Optional<Card> cardOptional = cardRepo.findCardByCardNumber(cardNumber);
        if (!cardOptional.isPresent())
            return ApiResponse.builder().success(false).message("This card is not present").build();
        Card card = cardOptional.get();
        card.setActive(false);
        cardRepo.save(card);
        return new ApiResponse(true,"deleted");
    }


    /**
     * Method that does random number 12 digits
     * for new card.
     *
     * @param startWith
     * @return
     */
    public Long randomCardNumber12(String startWith){
        Long cardNumber12 = (long) (Math.random() * 1000000000000L);
        String cardN = cardNumber12.toString();
        String cardNumber = startWith + cardN;
        Long originalCardNumber = Long.parseLong(cardNumber);

        return originalCardNumber;
    }
}
