package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller.QRGeneration;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("/api")
public class QRController {

    @GetMapping(value = "/generateBatchQR/{uuid}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getBatchQRCode(@PathVariable String uuid) {
        try {
            String url = "https://b9fa-2401-4900-1cd4-8818-c19a-8f13-80fd-acf9.ngrok-free.app/html/batchDetailsByID/" + uuid; // <-- use the HTML route
            byte[] image = QRCodeGenerator.generateQRCodeImage(url, 300, 300);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
