package com.example.IssueTracker.user.login;

import com.example.IssueTracker.project.ProjectDto;
import com.example.IssueTracker.user.CustomUserDetails;
import com.example.IssueTracker.user.SimpleUserDto;
import com.example.IssueTracker.user.User;
import com.example.IssueTracker.user.security.JWTTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/login")

public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<?> login (@RequestBody  LoginRequest loginRequest){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    loginRequest.getUsername(), loginRequest.getPassword()));


            User user = (User) authentication.getPrincipal();

            CustomUserDetails customUserDetails = new CustomUserDetails(user);


            return ResponseEntity.ok()
                    .header(HttpHeaders.AUTHORIZATION,
                            jwtTokenProvider.generateJWTToken(user)).body(customUserDetails);
        } catch (BadCredentialsException exception){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }



    }
}
