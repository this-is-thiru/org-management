package com.application.ene.orgmanagement.auth.dto;

import lombok.Data;

@Data
public class RegistrationRequest {
	private String clientId = "KANHA1";
	private String email;
	private String password;
	private String newPassword;
	private AuthHelper.Role role;
}
