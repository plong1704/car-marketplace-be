package com.market.carmarketservice.request.user;

import com.market.carmarketservice.model.user.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private Integer id;
    private String firstname;
    private String lastname;
    private String email;
    private String address;
    private String phone;
    private String avatar;
    private Integer birthyear;
    private String username;

    private Role role;
}
