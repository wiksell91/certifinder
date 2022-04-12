package com.example.certifinder.controller;



import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.security.spec.InvalidKeySpecException;
import com.example.certifinder.config.JWTTokenHelper;
import com.example.certifinder.model.Certuser;
import com.example.certifinder.responses.*;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.certifinder.requests.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTTokenHelper jWTTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) throws InvalidKeySpecException, NoSuchAlgorithmException {

        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUserName(), authenticationRequest.getPassword()));


        SecurityContextHolder.getContext().setAuthentication(authentication);

        Certuser user=(Certuser) authentication.getPrincipal();
        String jwtToken=jWTTokenHelper.generateToken(user.getUsername());

        LoginResponse response=new LoginResponse();
        response.setToken(jwtToken);


        return ResponseEntity.ok(response);
    }

    @GetMapping("/auth/userinfo")
    public ResponseEntity<?> getUserInfo(Principal certuser){
        Certuser userObj=(Certuser) userDetailsService.loadUserByUsername(certuser.getName());

        UserInfo userInfo=new UserInfo();
        userInfo.setFullName(userObj.getFullName());
        userInfo.setRoles(userObj.getAuthorities().toArray());


        return ResponseEntity.ok(userInfo);



    }
}
