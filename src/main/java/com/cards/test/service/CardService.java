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

import java.util.ArrayList;
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

    public List<Card> getFilteredAndSortedCards(Long user_id, String name, String color, String status, Integer page, Integer size, String sortBy) {
        List<Card> result = new ArrayList<>();

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));


        if(name == null) {
            result = cardRepository.findByMail(user_id);
        }else if(color ==null){
            result = cardRepository.findByMail(user_id);
        }else if(status == null){
            result = cardRepository.findByMail(user_id);
        }else if(!name.isEmpty()){
            Page<Card> cardPage = cardRepository.findCardsByName(name,pageable);
            result = cardPage.getContent();
        } else if(!color.isEmpty()){
            Page<Card> cardPage  = cardRepository.findCardByColor(color,pageable);
            result = cardPage.getContent();
        }


        return result;

    }


}
