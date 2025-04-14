package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

public class AuthResponse {
    private String role;

    public AuthResponse(String role) {
        this.role = role;
    }
    
    // Getters and Setters
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
}