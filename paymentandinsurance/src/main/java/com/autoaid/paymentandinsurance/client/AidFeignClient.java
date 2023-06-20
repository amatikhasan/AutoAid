package com.autoaid.paymentandinsurance.client;

import com.autoaid.paymentandinsurance.model.AidRequest;
import com.autoaid.paymentandinsurance.model.Breakdown;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "AidService")
public interface AidFeignClient {
    @GetMapping("/aidapi/aid/request/get/{id}")
    AidRequest getAidRequest(@PathVariable Long id);

    @PutMapping("/aidapi/aid/request/update")
    AidRequest updateAidRequest(@RequestBody AidRequest aidRequest);
}
