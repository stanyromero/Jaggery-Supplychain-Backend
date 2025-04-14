package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.BatchRequest;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.ProducerService;

@RestController
@RequestMapping("/api/producer")
public class ProducerController {
    @Autowired
    private ProducerService producerService;

    @PostMapping("/registerBatch")
    public ResponseEntity<?> registerGoods(@RequestBody BatchRequest batchRequest) {
        try {
            String uuid = producerService.registerBatch(batchRequest.getDateOfProduction(), batchRequest.getQuantityProduced());
            Map<String, String> response = new HashMap<>();
            response.put("uuid", uuid);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error registering goods: " + e.getMessage());
        }
    }
}
