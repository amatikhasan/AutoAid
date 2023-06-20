package com.autoaid.paymentandinsurance.controller;

import com.autoaid.paymentandinsurance.model.AidRequest;
import com.autoaid.paymentandinsurance.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/update/{id}")
    private String getAidRequest(Model model, @PathVariable Long id) {
        AidRequest aidRequest = paymentService.getAidRequest(id);
        model.addAttribute("aidRequest", aidRequest);
        return "update-payment";
    }
    
    @PostMapping("/update")
    private String updateAidRequest(AidRequest _aidRequest) {
        AidRequest aidRequest = paymentService.getAidRequest(_aidRequest.getId());
        aidRequest.setBillAmount(_aidRequest.getBillAmount());
        aidRequest.setPaymentStatus(_aidRequest.getPaymentStatus());
        aidRequest.setStatus(_aidRequest.getStatus());
        paymentService.updateAidRequest(aidRequest);
        return "redirect:http://localhost:8082/aid/all";
    }

}
