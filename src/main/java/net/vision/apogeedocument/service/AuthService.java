package net.vision.apogeedocument.service;

import net.vision.apogeedocument.dto.SignupRequest;

public interface AuthService {
    boolean createUser(SignupRequest signupRequest);
}
