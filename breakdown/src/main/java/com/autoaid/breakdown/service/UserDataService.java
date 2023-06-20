package com.autoaid.breakdown.service;

import com.autoaid.breakdown.model.UserInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataService {

    private final String STORAGE_FILE = "user_data.json";

    public void saveUser(UserInfo userInfo) {
        List<UserInfo> UserInfoList = new ArrayList<>();
        UserInfoList.add(userInfo);
        saveUserInfosToStorage(UserInfoList);
    }

    public UserInfo getUserInfo() {
        List<UserInfo> UserInfoList = getUserInfosFromStorage();
        if (!UserInfoList.isEmpty()) {
            return UserInfoList.get(0);
        }
        return null;
    }

    private List<UserInfo> getUserInfosFromStorage() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(STORAGE_FILE);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, new TypeReference<List<UserInfo>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void saveUserInfosToStorage(List<UserInfo> UserInfoList) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(STORAGE_FILE);
            objectMapper.writeValue(file, UserInfoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

