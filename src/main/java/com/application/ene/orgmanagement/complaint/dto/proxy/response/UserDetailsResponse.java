package com.application.ene.orgmanagement.complaint.dto.proxy.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {
//    private String id;
    private String clientId;
    private String userId;
    private String name;
    private String email;
    private boolean status;
    private String roles;
}
