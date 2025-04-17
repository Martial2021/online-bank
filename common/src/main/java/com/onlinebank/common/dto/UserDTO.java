package com.onlinebank.common.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO {
    private String id;
    private String email;
    private String firstname;
    private String lastname;
}
