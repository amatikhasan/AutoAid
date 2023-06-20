package com.autoaid.aid.service;

import com.autoaid.aid.client.BreakdownFeignClient;
import com.autoaid.aid.client.UserFeignClient;
import com.autoaid.aid.model.AidProvider;
import com.autoaid.aid.model.AidRequest;
import com.autoaid.aid.model.Breakdown;
import com.autoaid.aid.model.UserInfo;
import com.autoaid.aid.repository.AidProviderRepository;
import com.autoaid.aid.repository.AidRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AidService {

    private AidProviderRepository aidProviderRepository;
    private  AidRequestRepository aidRequestRepository;
    private BreakdownFeignClient breakdownFeignClient;
    private UserFeignClient userFeignClient;

    public AidService(AidProviderRepository aidProviderRepository, AidRequestRepository aidRequestRepository, BreakdownFeignClient breakdownFeignClient, UserFeignClient userFeignClient) {
        this.aidProviderRepository = aidProviderRepository;
        this.aidRequestRepository = aidRequestRepository;
        this.breakdownFeignClient = breakdownFeignClient;
        this.userFeignClient = userFeignClient;
    }

    public AidRequest createAidRequest(AidRequest aidRequest) {
        aidRequest.getLocation().setId(null);
        return aidRequestRepository.save(aidRequest);
    }

    public Optional<AidRequest> getAidRequest(Long id) {
        return aidRequestRepository.findById(id);
    }

    public List<AidRequest> getAllAidRequest() {
        return aidRequestRepository.findAll();
    }

    public List<AidRequest> getAllAidRequestById(Long userId) {
        return aidRequestRepository.findAllByUserId(userId);
    }

    public AidRequest updateAidRequest(AidRequest aidRequest) {
        return aidRequestRepository.save(aidRequest);
    }

    public void deleteAidRequest(Long id) {
        aidRequestRepository.deleteById(id);
    }

    public void deleteAllAidRequest() {
        aidRequestRepository.deleteAll();
    }

    public List<AidProvider> getAllAidProvider() {
        return aidProviderRepository.findAll();
    }
    public AidProvider createAidProvider(AidProvider aidProvider) {
        return aidProviderRepository.save(aidProvider);
    }

    public void deleteAllAidProvider() {
        aidProviderRepository.deleteAll();
    }


    public List<Breakdown> getAllBreakdown() {

        return breakdownFeignClient.getAllBreakdowns();
    }

    public Breakdown getBreakdown(long id) {
        return breakdownFeignClient.getBreakdownDetails(id);
    }
    public Breakdown updateBreakdown(Breakdown breakdown) {
        return breakdownFeignClient.updateBreakdown(breakdown);
    }

    public UserInfo getUserInfo(Long id) {
        return userFeignClient.getUserInfo(id);
    }

}
