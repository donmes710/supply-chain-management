package com.supplychain.supply_chain_management.controller;

import com.supplychain.supply_chain_management.config.JwtService;
import com.supplychain.supply_chain_management.dto.AuthRequest;
import com.supplychain.supply_chain_management.dto.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public JwtResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        if (authentication.isAuthenticated()) {
            // Since UserDetails is the principal, we can cast it to get the full user object
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return new JwtResponse(jwtService.generateToken(userDetails));
        } else {
            throw new UsernameNotFoundException("Invalid user request!");
        }
    }
}