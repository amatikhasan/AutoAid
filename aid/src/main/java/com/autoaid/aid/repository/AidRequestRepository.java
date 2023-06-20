package com.autoaid.aid.repository;

import com.autoaid.aid.model.AidRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AidRequestRepository extends JpaRepository<AidRequest, Long>{

    List<AidRequest> findAllByUserId(Long userId);

}
