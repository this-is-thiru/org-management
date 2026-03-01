package com.application.ene.orgmanagement.auth.dto;

import lombok.Data;

@Data
public class LoginRequest {
	private String clientId;
	private String username;
	private String password;
}
