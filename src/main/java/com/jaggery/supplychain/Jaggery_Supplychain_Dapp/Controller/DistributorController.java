package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.UpdateArrivalDateRequest;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.DistributorService;



@RestController
@RequestMapping("/api/distributor")
public class DistributorController {

    @Autowired
    private DistributorService distributorService;

    @PostMapping("/updateArrivalDate")
    public ResponseEntity<?> updateArrivalDate(@RequestBody UpdateArrivalDateRequest request) {
        try {
            String result = distributorService.updateArrivalDateToDistributor(request.getUuid(), request.getArrivalDate());
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error updating arrival date: " + e.getMessage());
        }
    }
}