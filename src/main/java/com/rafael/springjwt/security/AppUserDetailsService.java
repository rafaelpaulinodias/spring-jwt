package com.rafael.springjwt.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rafael.springjwt.model.UserInfo;
import com.rafael.springjwt.repository.UserInfoRepository;



@Service
public class AppUserDetailsService implements UserDetailsService  {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<UserInfo> userOp = userInfoRepository.findByEmail(email);
		UserInfo userInfo = userOp.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		return new SystemUserDetails(userInfo, getPermissions(userInfo));
	}

	private Collection<? extends GrantedAuthority> getPermissions(UserInfo userInfo) {
		Set<SimpleGrantedAuthority> permissions = new HashSet<>();
		userInfo.getPermissions().forEach(p -> permissions.add(new SimpleGrantedAuthority(p.getDescription().toUpperCase())));
		return permissions;
	}

}
