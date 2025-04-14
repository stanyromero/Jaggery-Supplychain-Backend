package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.BatchMetaDataDTO;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.DataService;


@RestController
@RequestMapping("/api")
public class DataController {

    @Autowired
    private DataService dataService;

    @GetMapping("/batchDetailsByID/{uuid}")
    public ResponseEntity<?> getGoodsDetailsByID(@PathVariable String uuid) {
        try {
            BatchMetaDataDTO batchDetails = dataService.getBatchDataByID(uuid);
            return ResponseEntity.ok(batchDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching goods details: " + e.getMessage());
        }
    }
}