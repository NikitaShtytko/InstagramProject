package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.AuthToken;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.security.JwtUser;
import com.netcracker.edu.fapi.security.TokenProvider;
import com.netcracker.edu.fapi.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

//AuthenticationController has API exposed to generate JWT toke
@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/token")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider tokenProvider;

    @Autowired
    private UserService userService;

    @PostMapping("/generate-token")
    public ResponseEntity generateToken(@RequestBody User user) {
        String login = user.getLogin();
        try {
            final Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            login,
                            user.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenProvider.generateToken(authentication);
            log.info("Token was generate");
            return ResponseEntity.ok(new AuthToken(token));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid login or password");
        }
    }

    @GetMapping("/details")
    public ResponseEntity getUserDetails() {
        SecurityContext context = SecurityContextHolder.getContext();
        try {
            Authentication authentication = context.getAuthentication();
            JwtUser details = (JwtUser) authentication.getPrincipal();
            return ResponseEntity.ok(details);
        }catch (ClassCastException e){
            throw new AuthenticationCredentialsNotFoundException("User details not found");
        }

    }
}
