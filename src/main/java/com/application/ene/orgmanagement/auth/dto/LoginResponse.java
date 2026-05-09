package com.application.ene.orgmanagement.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "from")
public class LoginResponse {
	private String userId;
	private String name;
	private String access_token;
	private int expires_in;
	private final String tokenType = "Bearer";
}
