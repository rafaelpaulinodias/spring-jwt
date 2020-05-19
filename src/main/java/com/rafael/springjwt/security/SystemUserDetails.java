package com.rafael.springjwt.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.rafael.springjwt.model.UserInfo;


public class SystemUserDetails extends User {
	
	private static final long serialVersionUID = 1L;
	
	private UserInfo userInfo;

	public SystemUserDetails(UserInfo userInfo, Collection<? extends GrantedAuthority> authorities) {
		super(userInfo.getEmail(), userInfo.getPassword(), authorities);
		this.userInfo = userInfo;
	}

	public UserInfo getUserInfo() {
		return this.userInfo;
	}
	
}
