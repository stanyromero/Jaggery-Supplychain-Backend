package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.InspectionDataByFSSAI2;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.FSSAI2Service;

@RestController
@RequestMapping("/api/fssai2")
public class FSSAI2Controller {

    @Autowired
    private FSSAI2Service fssai2Service;

    @PostMapping("/inspectFSSAI2")
    public ResponseEntity<?> inspectBatch(@RequestBody InspectionDataByFSSAI2 inspectionDataByFSSAI2) {
        try {
            String result = fssai2Service.verifyBatch(inspectionDataByFSSAI2);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error inspecting goods: " + e.getMessage());
        }
    }
    
}