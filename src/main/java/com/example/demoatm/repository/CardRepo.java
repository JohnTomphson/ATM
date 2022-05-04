package com.example.demoatm.repository;

import com.example.demoatm.models.Card;
import com.example.demoatm.projections.BankPro;
import com.example.demoatm.projections.CardPro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CardRepo extends JpaRepository<Card,Long> {
//    List<Card> findAllByActiveTrue();
   List<CardPro> findAllByActiveTrue();
   Optional<Card> findCardByCardNumber(Long number);
}
