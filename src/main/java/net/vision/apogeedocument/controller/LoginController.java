package net.vision.apogeedocument.controller;

import lombok.AllArgsConstructor;
import net.vision.apogeedocument.dto.LoginRequest;
import net.vision.apogeedocument.dto.LoginResponse;
import net.vision.apogeedocument.service.jwt.UserServiceImpl;
import net.vision.apogeedocument.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final  AuthenticationManager authenticationManager;

    private final UserServiceImpl userService;
    private final JwtUtils jwtUtils;

    @PostMapping(path = "login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try{
            authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );
        }catch (AuthenticationException e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try{
            userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        }catch (UsernameNotFoundException e){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }


        String jwt = jwtUtils.generateToken(userDetails.getUsername());
        return ResponseEntity.ok(new LoginResponse(jwt));
    }


}
