package com.hrban.web;

import lombok.Data;

@Data
public class LoginCommand {

    private String userName;

    private String password;
}
