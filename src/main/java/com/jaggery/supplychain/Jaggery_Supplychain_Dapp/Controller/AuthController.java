package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.AuthRequest;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.AuthResponse;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Model.User;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        String username = authRequest.getUsername();
        String password = authRequest.getPassword();

        User user = userService.authenticate(username,password);

        if (user != null) {
            return ResponseEntity.ok(new AuthResponse(user.getRole()));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }
}