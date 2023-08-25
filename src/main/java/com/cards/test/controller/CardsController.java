package com.cards.test.controller;

import com.cards.test.entity.Card;
import com.cards.test.entity.User;
import com.cards.test.model.CardModel;
import com.cards.test.model.UserModel;
import com.cards.test.service.CardService;
import com.cards.test.service.UserService;
import com.cards.test.util.CardStatus;
import com.cards.test.util.Erole;
import com.cards.test.util.ResourceNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.criteria.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

@RestController
@RequestMapping("/api/cards")
public class CardsController {

    @Autowired
    CardService cardService;

    @Autowired
    UserService userService;


    Map<String, Object> responseMap = new HashMap<>();


    @GetMapping("/fetch_cards")
    public ResponseEntity<?> getAllCards(@RequestParam Long user_id) {
        try {
            Erole role = userService.findUserById(user_id).get().getRole().iterator().next().getName();
            if (role.name().equals("ROLE_ADMIN")) {
                responseMap.put("code", "00");
                responseMap.put("status", cardService.getAllCards());
            }
        } catch (Exception e) {
            responseMap.put("code", "01");
            responseMap.put("status", "Fetching cards failed for this member of id " + user_id);
        }
        return ResponseEntity.ok(responseMap);

    }

    @GetMapping("/get_cards")
    public ResponseEntity<?> getUserCards(@RequestBody CardModel model) {
        try {
            responseMap.put("code", "00");
            responseMap.put("status", cardService.findByMail(model.getUser_id()));
        } catch (Exception e) {
            responseMap.put("code", "01");
            responseMap.put("status", "fetching cards failed for this member of id " + model.getUser_id());
        }
        return ResponseEntity.ok(responseMap);
    }

    @PutMapping("/update_card")
    public ResponseEntity<?> updateCard(@RequestBody CardModel model) {
        List<Card> cardx = cardService.findByMail(model.getUser_id());
        try {
            if (userService.findUserByID(model.getUser_id())) {
                Card card = cardx.get(model.getId() - 1);
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
            responseMap.put("status", "Card not updated:-" + model.getName());
        }
        return ResponseEntity.ok(responseMap);

    }

    @PostMapping("/create_card")
    public ResponseEntity<?> createCard(@RequestBody CardModel model) {

        try {
            Card card = new Card();
            if (userService.findUserByID(model.getUser_id())) {
                card.setName(model.getName());
                card.setColor(model.getColor());
                card.setDescription(model.getDesc());
                card.setUser_id(model.getUser_id());
                card.setCreated_at(new Date());
                card.setUpdated_at(new Date());
                card.setStatus(CardStatus.TO_DO);
                responseMap.put("code", "00");
                responseMap.put("status", cardService.saveCard(card));
            }
        } catch (Exception e) {
            responseMap.put("code", "01");
            responseMap.put("status", "Card not updated:-" + model.getName());
        }
        return ResponseEntity.ok(responseMap);
    }

    @DeleteMapping("/del_card")
    public ResponseEntity<?> deleteCard(@RequestBody CardModel model) {
        List<Card> card = cardService.findByMail(model.getUser_id());
        try {
            if (userService.findUserByID(model.getUser_id())) {
                cardService.deleteCardById(card.get(model.getId() - 1).getId());
                responseMap.put("code", "00");
                responseMap.put("status", "Card Successfully deleted");
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("code", "01");
            responseMap.put("status", "Card not deleted");
        }
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping("/get_filtered_cards")
    public ResponseEntity<?> getFilteredAndSortedCards(
            @RequestParam(required = false) Long user_id,
            @RequestParam String color,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "3") Integer size,
            @RequestParam(required = false) String sort) {
        try {
            List<Card> cards = cardService.getFilteredAndSortedCards(user_id, color, page, size, sort);
            responseMap.put("code", "00");
            responseMap.put("status", cards);
        } catch (Exception e) {
            e.printStackTrace();
            responseMap.put("code", "01");
            responseMap.put("status", "Card not fetched");
        }
        return ResponseEntity.ok(responseMap);
    }

}
