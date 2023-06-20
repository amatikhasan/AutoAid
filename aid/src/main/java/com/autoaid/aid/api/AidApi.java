package com.autoaid.aid.api;

import com.autoaid.aid.model.AidProvider;
import com.autoaid.aid.model.AidRequest;
import com.autoaid.aid.model.Breakdown;
import com.autoaid.aid.service.AidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aidapi")
public class AidApi {

    private AidService aidService;

    @Autowired
    public AidApi(AidService aidService) {
        this.aidService = aidService;
    }

    @DeleteMapping("/delete/all")
    private void deleteAllAid() {
        aidService.deleteAllAidRequest();
    }

    @GetMapping("/provider/all")
    private List<AidProvider> getAllAidProviders() {
        return aidService.getAllAidProvider();
    }

    @PostMapping("/provider/create")
    private AidProvider createAidProvider(AidProvider aidProvider) {
        return aidService.createAidProvider(aidProvider);
    }

    @DeleteMapping("/provider/delete/all")
    private void deleteAllAidProvider() {
        aidService.deleteAllAidProvider();
    }

    @GetMapping("/breakdowns/all")
    private List<Breakdown> getAllBreakdown() {
        return aidService.getAllBreakdown();
    }

    @PostMapping("/aid/request")
    private AidRequest requestAid(@RequestBody AidRequest aidRequest) {
        return aidService.createAidRequest(aidRequest);
    }

    @GetMapping("/aid/getmyrequests/{userId}")
    private List<AidRequest> getMyRequests(@PathVariable Long userId) {
        return aidService.getAllAidRequestById(userId);
    }


    @PutMapping("/aid/request/update")
    private AidRequest updateAidrequest(@RequestBody AidRequest aidRequest) {
        return aidService.updateAidRequest(aidRequest);
    }

    @GetMapping("/aid/request/get/{id}")
    private AidRequest requestAid(@PathVariable Long id) {
        return aidService.getAidRequest(id).get();
    }

}
