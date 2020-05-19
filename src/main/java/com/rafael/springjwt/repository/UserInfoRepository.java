package com.rafael.springjwt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rafael.springjwt.model.UserInfo;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
	
	public Optional<UserInfo> findByEmail(String email);
	
}
