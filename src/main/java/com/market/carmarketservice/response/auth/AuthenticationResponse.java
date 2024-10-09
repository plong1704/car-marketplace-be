package com.market.carmarketservice.response.auth;

import com.market.carmarketservice.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String token;

    private Integer userID;
    private String avatar;
    private Role role;
}
