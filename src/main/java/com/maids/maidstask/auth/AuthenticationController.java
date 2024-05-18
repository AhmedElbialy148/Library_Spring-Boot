package com.maids.maidstask.auth;

import com.maids.maidstask.auth.Dtos.LoginRequestDto;
import com.maids.maidstask.auth.Dtos.LoginResponseDto;
import com.maids.maidstask.auth.Dtos.RegisterRequestDto;
import com.maids.maidstask.auth.Dtos.RegisterResponseDto;
import com.maids.maidstask.exceptions.BadRequestException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/api/patrons/register")
    public ResponseEntity<RegisterResponseDto>  register(@Valid @RequestBody RegisterRequestDto userData){
        if(userData.username().isEmpty()){
            throw new BadRequestException("Username cannot be empty");
        }
        return new ResponseEntity<>(authenticationService.register(userData), HttpStatus.CREATED) ;
    }

    @PostMapping("/api/patrons/login")
    public ResponseEntity<LoginResponseDto>  login(@Valid @RequestBody LoginRequestDto userData){
        return new ResponseEntity<>(authenticationService.login(userData), HttpStatus.OK) ;
    }
}
