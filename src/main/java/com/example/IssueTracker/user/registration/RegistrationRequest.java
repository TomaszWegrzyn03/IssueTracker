package com.example.IssueTracker.user.registration;

import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationRequest {

    private  String username;
    private  String email;
    private  String password;
}
