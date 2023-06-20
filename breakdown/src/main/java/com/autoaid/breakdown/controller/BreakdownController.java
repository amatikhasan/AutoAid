package com.autoaid.breakdown.controller;

import com.autoaid.breakdown.model.*;
import com.autoaid.breakdown.service.BreakdownService;
import com.autoaid.breakdown.service.GeocodingService;
import com.autoaid.breakdown.service.UserDataService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/breakdown")
public class BreakdownController {

    private BreakdownService breakdownService;
    private UserDataService userDataService;
    private GeocodingService geocodingService;

    @Autowired
    public BreakdownController(BreakdownService BreakdownService, UserDataService userDataService, GeocodingService geocodingService) {
        this.breakdownService = BreakdownService;
        this.userDataService = userDataService;
        this.geocodingService = geocodingService;
    }

    @GetMapping
    public String dashboard(Model model, HttpServletRequest request) throws IOException {
        UserInfo user = null;
        // Retrieve session from session ID
        String userId = request.getParameter("userId");

        HttpSession session = request.getSession();
        session.setAttribute("ara-user-id", userId);
        /*Object sessionId = session.getAttribute("ara-user-id");
        if (sessionId != null)
            userId = sessionId.toString();*/

        if (userId != null)
            user = breakdownService.getUserInfo(Long.valueOf(userId));

        if (user != null) {
            session.setAttribute("ara-user-role", user.getRole());

            return "redirect:/breakdown/details";
        } else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @GetMapping("/all")
    private String getAllTestBreakdown(Model model) throws IOException {
        List<Breakdown> testData = breakdownService.getAllTestBreakdown();
        model.addAttribute("breakdowns", testData);

        for (Breakdown testCase : testData) {
            model.addAttribute("model", testCase.getModel());
            model.addAttribute("timestamp", testCase.getTimestamp().toString());
            model.addAttribute("location", (testCase.getLocation().getLatitude() + "(lat) " + testCase.getLocation().getLongitude() + "(lon)").toString());
            model.addAttribute("Hardware Fault Code", testCase.getErrorCode());
        }

        return "breakdown-list";
    }

    @GetMapping("/details")
    private String getAllBreakdown(Model model, HttpServletRequest request) {
        List<Breakdown> breakdowns = new ArrayList<>();
        List<GeoLocation> providerGeoLocationList = new ArrayList<>();
        String userId = null;
        String userRole = null;
        UserInfo user = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-user-id");
        if (sessionId != null) {
            userId = sessionId.toString();
            System.out.println("User ID: " + userId);
        }

        if (userId != null) {
            user = breakdownService.getUserInfo(Long.valueOf(userId));
            userRole = user.getRole();
        }

        if (userId != null && userRole != null && userRole.equals("Driver"))
            breakdowns = breakdownService.getAllBreakdownByUserId(Integer.parseInt(userId));
        else
            breakdowns = breakdownService.getAllBreakdown();
        List<UserInfo> aidProviders = breakdownService.getProviderList();

        for (UserInfo aidProvider : aidProviders) {
            String coordinates = geocodingService.getCoordinates(aidProvider.getAddress());

            GeoLocation geoLocation = geocodingService.getCoordinatesFromResponse(coordinates);
            aidProvider.setLocation(geoLocation);
        }

        model.addAttribute("breakdownList", breakdowns);
        model.addAttribute("aidProviderList", aidProviders);

        if (user != null) {
            session.setAttribute("ara-user-role", user.getRole());

            return "breakdown-details";
        } else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @GetMapping("/create")
    private String showCreateBreakdownPage() {
        return "breakdown";
    }

    @PostMapping("/create")
    private String createBreakdown(HttpServletRequest request) throws IOException {
        String userId = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-user-id");
        if (sessionId != null)
            userId = sessionId.toString();

        if (userId != null) {
            breakdownService.createRandomBreakdown(userId);
            return "redirect:/breakdown/details";
        } else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @GetMapping("/get/{id}")
    private Breakdown getBreakdown(@PathVariable Long id) {
        return breakdownService.getBreakdown(id).get();
    }

    @PutMapping("/update")
    private Breakdown updateBreakdown(Breakdown Breakdown) {
        return breakdownService.updateBreakdown(Breakdown);
    }

    @DeleteMapping("/delete/{id}")
    private void deleteBreakdown(@PathVariable Long id) {
        breakdownService.deleteBreakdown(id);
    }

    @PostMapping("/aid/request")
    private String requestAid(Breakdown _breakdown, HttpServletRequest request) {
        String userId = null;
        UserInfo user = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-user-id");
        if (sessionId != null)
            userId = sessionId.toString();

        if (userId != null)
            user = breakdownService.getUserInfo(Long.valueOf(userId));

        Breakdown breakdown = breakdownService.getBreakdown(_breakdown.getId()).get();
        AidRequest aidRequest = new AidRequest();
        aidRequest.setBreakdownId(breakdown.getId().intValue());
        aidRequest.setCarModel(breakdown.getModel());
        aidRequest.setErrorCode(breakdown.getErrorCode());
        aidRequest.setLocation(breakdown.getLocation());
        aidRequest.setTimeStamp(breakdown.getTimestamp());
        if (user != null) {
            aidRequest.setUserId(Integer.parseInt(userId));
            aidRequest.setCarDriver(user.getName());
        }
        //aidRequest.setAidProvider("Wanna fix your car GmbH");
        aidRequest.setStatus("Pending");
        aidRequest.setDescription("");
        aidRequest.setPaymentStatus("Pending");
        AidRequest _aidRequest = breakdownService.requestAid(aidRequest);
        /*if (_aidRequest != null) return "redirect:http://localhost:8082/aid/all";
        else*/
            return "redirect:/breakdown/details";
    }

    @GetMapping("/aid/myrequests")
    private String getMyRequests(Model model, HttpServletRequest request) {
        List<AidRequest> aidRequests = new ArrayList<>();
        String userId = null;
        HttpSession session = request.getSession();
        Object sessionId = session.getAttribute("ara-user-id");
        if (sessionId != null)
            userId = sessionId.toString();

        if (userId != null) {
            aidRequests = breakdownService.getMyRequests(Long.valueOf(userId));
            model.addAttribute("aidRequests", aidRequests);
            return "aid-details";
        }else {
            return "redirect:http://localhost:8084/user/login";
        }
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("ara-user-id", null);

        return "redirect:http://localhost:8084/user/login";
    }

    @GetMapping("/map/{lat}/{lon}")
    public String showMap(@PathVariable double lat, @PathVariable double lon, Model model) {

        model.addAttribute("latitude", lat);
        model.addAttribute("longitude", lon);

        return "map";
    }

}
