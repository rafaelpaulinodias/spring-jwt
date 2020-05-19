package com.rafael.springjwt.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerateUtil {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("strongpassword"));
	}
	
	// $2a$10$7OKxfhJZrHbiJpaAIDcb5eshOlOW7bAMJwPHbXf9Z6gRQcfFc22sy user
	// $2a$10$qwR.WNKW8RyrY0bAwAel1OSd2NtBIRoXJZK1..nKRF02DtqFT4/ZK admin
	
}
