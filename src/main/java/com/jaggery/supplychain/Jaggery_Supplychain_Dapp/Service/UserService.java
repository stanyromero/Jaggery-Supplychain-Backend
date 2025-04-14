package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Model.User;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User authenticate(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password).orElse(null);
    }
}
