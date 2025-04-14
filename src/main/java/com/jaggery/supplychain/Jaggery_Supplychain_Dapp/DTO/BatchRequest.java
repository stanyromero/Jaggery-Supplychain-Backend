package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

import java.math.BigInteger;

public class BatchRequest {
    private String dateOfProduction;
    private BigInteger quantityProduced;

    public String getDateOfProduction() {
        return dateOfProduction;
    }

    public void setDateOfProduction(String dateOfProduction) {
        this.dateOfProduction = dateOfProduction;
    }

    public BigInteger getQuantityProduced() {
        return quantityProduced;
    }
    
    public void setQuantityProduced(BigInteger quantityProduced) {
        this.quantityProduced = quantityProduced;
    }

    
}
