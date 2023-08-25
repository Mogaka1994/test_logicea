package com.cards.test.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, String email, List<String> roles) {
        this.token = accessToken;
        this.email = email;
        this.roles = roles;
    }

}