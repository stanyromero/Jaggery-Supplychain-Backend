package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

import java.math.BigInteger;

public class BatchMetaDataDTO {
    private String Uuid;
    private String dateOfProduction;
    private BigInteger quantityProduced;
    private String arrivalDateToDistributor;
    private String producer;
    private String distributor;
    private InspectionDataDTO inspectionByFSSAI1;
    private InspectionDataDTO inspectionByFSSAI2;
    private Boolean isArrivedToDistributor;

    // Getters and Setters
    public String getUuid() {
        return Uuid;
    }

    public void setUuid(String Uuid) {
        this.Uuid = Uuid;
    }

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

    public String getArrivalDateToDistributor() {
        return arrivalDateToDistributor;
    }

    public void setArrivalDateToDistributor(String arrivalDateToDistributor) {
        this.arrivalDateToDistributor = arrivalDateToDistributor;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getDistributor() {
        return distributor;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public InspectionDataDTO getInspectionByFSSAI1() {
        return inspectionByFSSAI1;
    }

    public void setInspectionByFSSAI1(InspectionDataDTO inspectionByFSSAI1) {
        this.inspectionByFSSAI1 = inspectionByFSSAI1;
    }

    public InspectionDataDTO getInspectionByFSSAI2() {
        return inspectionByFSSAI2;
    }

    public void setInspectionByFSSAI2(InspectionDataDTO inspectionByFSSAI2) {
        this.inspectionByFSSAI2 = inspectionByFSSAI2;
    }

    public Boolean getIsArrivedToDistributor() {
        return isArrivedToDistributor;
    }

    public void setIsArrivedToDistributor(Boolean isArrivedToDistributor) {
        this.isArrivedToDistributor = isArrivedToDistributor;
    }
}

