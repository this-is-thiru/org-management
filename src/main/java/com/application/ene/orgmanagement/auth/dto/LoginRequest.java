package com.application.ene.orgmanagement.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private LoginType loginType = LoginType.USER_ID_PASSWORD;
	private String clientId;
	private String email;
	private String username;
	private String password;
}
