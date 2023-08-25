package com.cards.test.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"user_id","id","name","color","desc","status"})
public class CardModel {

    private Long user_id;

    private Integer id;

    private String name;

    private String color;

    private String desc;

    private String status;




}
