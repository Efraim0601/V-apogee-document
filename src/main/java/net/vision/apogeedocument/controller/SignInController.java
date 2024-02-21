package net.vision.apogeedocument.controller;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import net.vision.apogeedocument.dto.SignupRequest;
import net.vision.apogeedocument.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SignInController {

    private final AuthService authService;

    @PostMapping(path = "signup")
    public ResponseEntity<String> sing_up_user(@RequestBody SignupRequest signupRequest){
        boolean isUserCreated = authService.createUser(signupRequest);
        if (isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("Custumer created succesfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create custumer");
    }

}
