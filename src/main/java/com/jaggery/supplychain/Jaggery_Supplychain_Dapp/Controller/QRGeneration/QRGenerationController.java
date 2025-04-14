package com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Controller.QRGeneration;

import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.DTO.BatchMetaDataDTO;
import com.jaggery.supplychain.Jaggery_Supplychain_Dapp.Service.DataService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class QRGenerationController {

    @Autowired
    private DataService dataService;

    @GetMapping("/html/batchDetailsByID/{uuid}")
    public String getBatchDetailsHTML(@PathVariable String uuid, Model model) {
        try {
            BatchMetaDataDTO details = dataService.getBatchDataByID(uuid);
            model.addAttribute("batch", details);
            return "batch-details";  // Thymeleaf template name (batch-details.html)
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }
}
