package com.ynov.webServices.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class UserDTO {
    private int id;
    private String name;
    private String email;
}
