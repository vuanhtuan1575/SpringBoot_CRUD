package com.vnpay.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReseponseUserDto {

    private Long id;

    private String username;

    private String name;

    private String phone;

    private String roleName;
}
