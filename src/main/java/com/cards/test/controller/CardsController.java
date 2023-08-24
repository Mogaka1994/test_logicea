package com.cards.test.controller;

import com.cards.test.entity.Card;
import com.cards.test.entity.User;
import com.cards.test.model.CardModel;
import com.cards.test.model.UserModel;
import com.cards.test.service.CardService;
import com.cards.test.service.UserService;
import com.cards.test.util.CardStatus;
import com.cards.test.util.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;


    Map<String, Object> responseMap = new HashMap<>();


    @GetMapping("/fetch_cards")
//    @PreAuthorize("hasRole('ADMIN')")
    public List<Card> getAllCards() {
        return cardService.getAllCards();
    }

    @GetMapping("/get_cards")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUserCards(@RequestBody CardModel model) {
        return ResponseEntity.ok(cardService.findByMail(model.getUser_id()));
    }

    @PutMapping("/update_card")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCard(@RequestBody CardModel model) throws JsonProcessingException {
        List<Card> cardx = cardService.findByMail(model.getUser_id());
        try {
            if(userService.findUserByID(model.getUser_id())) {
                Card card = cardx.get(model.getId()-1);
                card.setColor(model.getColor());
                card.setName(model.getName());
                card.setDescription(model.getDesc());
                card.setStatus(CardStatus.IN_PROGRESS);
                cardService.saveCard(card);
                responseMap.put("code", "00");
                responseMap.put("status", "Card Successfully updated");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("code", "01");
            responseMap.put("status", "Card not updated:-"+model.getName());
        }
        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(responseMap));

    }

    @PostMapping("/create_card")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Card> createCard(@RequestBody CardModel model) {
        Card card = new Card();
        if(userService.findUserByID(model.getUser_id())) {
            card.setName(model.getName());
            card.setColor(model.getColor());
            card.setDescription(model.getDesc());
            card.setUser_id(model.getUser_id());
            card.setCreated_at(new Date());
            card.setUpdated_at( new Date());
            card.setStatus(CardStatus.TO_DO);
        }
        return ResponseEntity.ok(cardService.saveCard(card));
    }

    @DeleteMapping("/del_card")
    public ResponseEntity<?> deleteCard(@RequestBody CardModel model) throws JsonProcessingException {
        List<Card> card = cardService.findByMail(model.getUser_id());
        try {
            if(userService.findUserByID(model.getUser_id())) {
                cardService.deleteCardById(card.get(model.getId() -1).getId());
                responseMap.put("code", "00");
                responseMap.put("status", "Card Successfully deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("code", "01");
            responseMap.put("status", "Card not deleted");
        }
        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(responseMap));
    }

    @GetMapping("/get_filtered_cards")
    public ResponseEntity<?> getFilteredAndSortedCards(
            @RequestParam(required = false) String user_id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) String sortBy) {
        List<Card> cards = cardService.getFilteredAndSortedCards(user_id,name, color, status, page, size, sortBy);
        return ResponseEntity.ok(cards);
    }
}
