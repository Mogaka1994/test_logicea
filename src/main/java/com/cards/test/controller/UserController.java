package com.cards.test.controller;


import com.cards.test.entity.Roles;
import com.cards.test.entity.User;
import com.cards.test.model.UserModel;
import com.cards.test.service.UserService;
import com.cards.test.util.Erole;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.*;

@RestController
@Api(tags = "Cards System")
@RequestMapping("/api/cards")
public class UserController {

    Map<String, Object> responseMap = new HashMap<>();

    Set<Roles> roles = new HashSet<>();

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;


    @ApiOperation(value = "Create users of card system.")
    @PostMapping("/create_user")
    public ResponseEntity<String> createUser(@RequestBody UserModel model) throws JsonProcessingException {
        User user = new User();
        try {
            user.setEmail(model.getEmail());
            user.setPassword(encoder.encode(model.getPassword()));
            user.setStatus(1);
            if (!userService.checkIfRegistered(model.getEmail())) {
                if (model.getRole() == null) {
                    Roles userRole = userService.findByName(Erole.ROLE_MEMBER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                } else {
                    model.getRole().forEach(role -> {
                        switch (role) {
                            case "admin":
                                Roles adminRole = userService.findByName(Erole.ROLE_ADMIN)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(adminRole);
                                break;
                            case "mod":
                                Roles modRole = userService.findByName(Erole.ROLE_MEMBER)
                                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                                roles.add(modRole);
                                break;
                        }
                    });
                    user.setRole(roles);
                }
                userService.saveUser(user);
                responseMap.put("code", "00");
                responseMap.put("status", "Successfully registered");
            } else {
                responseMap.put("code", "01");
                responseMap.put("status", "User exists registered");
            }
        } catch (Exception e) {
            responseMap.put("code", "01");
            responseMap.put("status", e.getMessage());
        }
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(responseMap), HttpStatus.OK);

    }

}
