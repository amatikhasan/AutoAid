package com.autoaid.aid.client;

import com.autoaid.aid.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UserService")
public interface UserFeignClient {
    @GetMapping("/userapi/get/{id}")
    UserInfo getUserInfo(@PathVariable Long id);

    @GetMapping("/userapi/get/providers")
    List<UserInfo> getProviderList();
}
