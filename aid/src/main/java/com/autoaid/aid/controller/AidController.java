package com.autoaid.aid.controller;

import com.autoaid.aid.model.AidRequest;
import com.autoaid.aid.model.Breakdown;
import com.autoaid.aid.model.UserInfo;
import com.autoaid.aid.service.AidService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/aid")
public class AidController {

    private AidService aidService;

    @Autowired
    public AidController(AidService aidService) {
        this.aidService = aidService;
    }

    @GetMapping("/all")
    private String getAllAidRequest(Model model, HttpServletRequest request) {
        // Retrieve session from session ID
        String userId = request.getParameter("userId");

        HttpSession session = request.getSession();
        session.setAttribute("ara-provider-id", userId);

        if (userId != null) {
            model.addAttribute("aidRequestList", aidService.getAllAidRequest());
            return "aid-details";
        } else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @PostMapping("/create")
    private String createAidRequest(AidRequest aidRequest) {
        aidService.createAidRequest(aidRequest);
        return "redirect:/aid-form";
    }

    @GetMapping("/payment")
    private String updateAidPayment(AidRequest aidRequest) {
        return "redirect:http://localhost:8083/payment/update/" + aidRequest.getId();
    }

    @GetMapping("/get/{id}")
    private AidRequest getAidRequest(@PathVariable Long id) {
        return aidService.getAidRequest(id).get();
    }

    @GetMapping("/getAllByProvider")
    private List<AidRequest> getAidRequestsByProvider(HttpServletRequest request) {
        List<AidRequest> aidRequestList = new ArrayList<>();
        UserInfo user = null;
        String userId = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-provider-id");

        if (sessionId != null) {
            userId = sessionId.toString();
            System.out.println("User ID: " + userId);
        }
        if (userId != null)
            aidRequestList = aidService.getAllAidRequestById(Long.valueOf(userId));
        return aidRequestList;
    }

    @PutMapping("/update")
    private AidRequest updateAidRequest(AidRequest aidRequest) {
        return aidService.updateAidRequest(aidRequest);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteAidRequest(@PathVariable Long id) {
        aidService.deleteAidRequest(id);
    }

    @PostMapping("/breakdown/update/{id}")
    private String updateBreakdown(@PathVariable Long id, HttpServletRequest request) {
        UserInfo user = null;
        String userId = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-provider-id");

        if (sessionId != null) {
            userId = sessionId.toString();
            System.out.println("User ID: " + userId);
        }

        if (userId != null)
            user = aidService.getUserInfo(Long.valueOf(userId));

        AidRequest aidRequest = aidService.getAidRequest(id).get();
        if (aidRequest != null && user != null) {
            aidRequest.setAidProvider(user.getName());
            aidService.updateAidRequest(aidRequest);
        }
        Breakdown breakdown = aidService.getBreakdown(aidRequest.getBreakdownId());
        if (breakdown != null && user != null) {
            breakdown.setProviderName(user.getName());
            aidService.updateBreakdown(breakdown);
        }
        return "redirect:/aid/all";
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("ara-provider-id", null);

        return "redirect:http://localhost:8084/user/login";
    }
}
