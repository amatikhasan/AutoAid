package com.autoaid.user.repository;

import com.autoaid.user.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long>{
    Optional<UserInfo> findByEmailAndPassword(String email, String password);
    List<UserInfo> findAllByRole(String role);
}
