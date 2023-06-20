package com.autoaid.breakdown.client;

import com.autoaid.breakdown.model.AidRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "AidService")
public interface AidFeignClient {

    @PostMapping("/aidapi/aid/request")
    AidRequest requestAid(@RequestBody AidRequest aidRequest);

    @GetMapping("/aidapi/aid/getmyrequests/{userId}")
    List <AidRequest> getMyRequests(@PathVariable Long userId);
}
