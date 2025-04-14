package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO;

public class InspectionDataByFSSAI2 {
    private String uuid;

    // Getters and Setters
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        // Validate UUID format
        if (uuid == null || !uuid.matches("0x[0-9a-fA-F]{64}")) {
            throw new IllegalArgumentException("UUID must be a 64-character hexadecimal string prefixed with '0x'");
        }
        this.uuid = uuid;
    }

    String inspectorId;
    String dateOfInspection;
    String modeOfInspection; // Onsite / Remote
    Boolean sampleTested;       // true => Yes, false => No
    String approvalStatus;   // Approved / Rejected
    String remarks;


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
}
