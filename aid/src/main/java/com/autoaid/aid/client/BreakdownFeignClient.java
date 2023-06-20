package com.autoaid.aid.client;

import com.autoaid.aid.model.Breakdown;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "BreakdownService")
public interface BreakdownFeignClient {
    @GetMapping("/breakdownapi/all")
    List<Breakdown> getAllBreakdowns();

    @GetMapping("/breakdownapi/get/{id}")
    Breakdown getBreakdownDetails(@PathVariable Long id);

    @PutMapping("/breakdownapi/update")
    Breakdown updateBreakdown(@RequestBody Breakdown breakdown);
}
