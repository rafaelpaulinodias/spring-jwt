package com.rafael.springjwt.resource;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafael.springjwt.config.property.ApiProperty;

@RestController
@RequestMapping("/tokens")
public class TokenResource {
	
	@Autowired
	private ApiProperty property;
	
	@DeleteMapping
	public void revoke(HttpServletRequest request, HttpServletResponse response) {
		Cookie cookie = new Cookie("refreshToken", null);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath() + "/oauth/token");
		cookie.setSecure(property.getSecurity().isEnableHttps());
		response.addCookie(cookie);
		response.setStatus(HttpStatus.NO_CONTENT.value());
	}

}
