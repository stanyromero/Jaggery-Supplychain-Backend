package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.InspectionDataByFSSAI1;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.FSSAI1Service;

@RestController
@RequestMapping("/api/fssai1")
public class FSSAI1Controller {

    @Autowired
    private FSSAI1Service fssai1Service;

    @PostMapping("/inspectFSSAI1")
    public ResponseEntity<?> inspectBatch(@RequestBody InspectionDataByFSSAI1 inspectionDataByFSSAI1) {
        try {
            String result = fssai1Service.verifyBatch(inspectionDataByFSSAI1);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inspecting goods: " + e.getMessage());
        }
    }
    
}