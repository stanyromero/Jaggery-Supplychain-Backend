package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

public class UpdateArrivalDateRequest {
    private String uuid;
    private String arrivalDate;

    // Getters and Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate = arrivalDate;
    }
}