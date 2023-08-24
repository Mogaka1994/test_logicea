package com.cards.test.service;

import com.cards.test.entity.Card;
import com.cards.test.entity.User;
import com.cards.test.respository.CardRepository;
import com.cards.test.respository.UserRepository;
import com.cards.test.util.CardStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Card> getAllCards() {
        return cardRepository.findAll();
    }

    public List<Card> findByMail(Long user_id) {
        return cardRepository.findByMail(user_id);
    }

    public Card saveCard(Card card) {
        return cardRepository.save(card);
    }

    public void deleteCardById(Long id) {
        cardRepository.deleteById(id);
    }

    public List<Card> getFilteredAndSortedCards(String user_id, String name, String color, String status, Integer page, Integer size, String sortBy) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
//
//        User currentUser = userRepository.findByEmail(user_id).get();
//
//        Page<Card> cardPage = cardRepository.findByUserAndNameContainingAndColorContainingAndStatusContaining(
//                currentUser, name, color, status, pageable);
//
//        return cardPage.getContent();
        return null;
    }


}
