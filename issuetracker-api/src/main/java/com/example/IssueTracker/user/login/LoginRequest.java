package com.example.IssueTracker.user.login;

import lombok.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LoginRequest {

    private  String username;
    private  String password;
}