package com.ynov.webServices.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
}
