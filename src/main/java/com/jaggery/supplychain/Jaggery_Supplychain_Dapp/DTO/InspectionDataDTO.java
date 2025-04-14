package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

public class InspectionDataDTO {
    private String inspectorId;
    private String dateOfInspection;
    private String modeOfInspection;
    private Boolean sampleTested;
    private String approvalStatus;
    private String remarks;
    
    public String getInspectorId() {
        return inspectorId;
    }
    public void setInspectorId(String inspectorId) {
        this.inspectorId = inspectorId;
    }
    public String getDateOfInspection() {
        return dateOfInspection;
    }
    public void setDateOfInspection(String dateOfInspection) {
        this.dateOfInspection = dateOfInspection;
    }
    public String getModeOfInspection() {
        return modeOfInspection;
    }
    public void setModeOfInspection(String modeOfInspection) {
        this.modeOfInspection = modeOfInspection;
    }
    public Boolean getSampleTested() {
        return sampleTested;
    }
    public void setSampleTested(Boolean sampleTested) {
        this.sampleTested = sampleTested;
    }
    public String getApprovalStatus() {
        return approvalStatus;
    }
    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }
    public String getRemarks() {
        return remarks;
    }
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    // Getters and Setters
    
}
