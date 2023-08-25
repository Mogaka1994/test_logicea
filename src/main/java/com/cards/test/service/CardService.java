package com.cards.test.service;

import com.cards.test.entity.Card;
import com.cards.test.respository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {
    @Autowired
    private CardRepository cardRepository;

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

    public List<Card> getFilteredAndSortedCards(Long user_id, String color,Integer page, Integer size, String sortBy) {
        List<Card> result = cardRepository.findByMail(user_id);

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        if (color != null || !color.isEmpty()) {
            Page<Card> cardPage = cardRepository.findByByColor(color, user_id, pageable);
            result = cardPage.getContent();
        }


        return result;

    }

    public List<Card> getAllCardx(Pageable pagingSort) {
        return cardRepository.findAllCardx(pagingSort);

    }
}



