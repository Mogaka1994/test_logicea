package com.cards.test.entity;


import com.cards.test.util.CardStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "cards")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false, length = 30)
    private Long user_id;
    @Column(name = "name", nullable = false, length = 30)
    private String name;
    @Column(name = "description", nullable = false, length = 30)
    private String description;
    @Column(name = "color", nullable = false, length = 6)
    private String color;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    @Column(name = "created_at", nullable = false, length = 30)
    private Date created_at;

    @Column(name = "updated_at", nullable = false, length = 30)
    private Date updated_at;
}


