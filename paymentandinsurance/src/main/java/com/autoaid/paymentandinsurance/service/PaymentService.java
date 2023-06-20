package com.autoaid.paymentandinsurance.service;

import com.autoaid.paymentandinsurance.client.AidFeignClient;
import com.autoaid.paymentandinsurance.model.AidRequest;
import com.autoaid.paymentandinsurance.repository.AidRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class PaymentService {

    @Autowired
    AidFeignClient aidFeignClient;
    private AidRequestRepository aidRequestRepository;

    public PaymentService(AidRequestRepository aidRequestRepository, AidFeignClient aidFeignClient) {
        this.aidRequestRepository = aidRequestRepository;
        this.aidFeignClient = aidFeignClient;
    }

    public AidRequest createAidRequest(AidRequest aidRequest) {
        return aidRequestRepository.save(aidRequest);
    }

    public AidRequest getAidRequest(Long id) {
        return aidFeignClient.getAidRequest(id);
    }

    public List<AidRequest> getAllAidRequest() {
        return aidRequestRepository.findAll();
    }

    public AidRequest updateAidRequest(AidRequest aidRequest) {
        return aidFeignClient.updateAidRequest(aidRequest);
    }

    public void deleteAidRequest(Long id) {
        aidRequestRepository.deleteById(id);
    }

    public void deleteAllAidRequest() {
        aidRequestRepository.deleteAll();
    }

}
