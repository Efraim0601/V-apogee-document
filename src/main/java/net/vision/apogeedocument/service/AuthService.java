package net.vision.apogeedocument.service;

import net.vision.apogeedocument.dto.SignupRequest;
import net.vision.apogeedocument.entities.User;

public interface AuthService {
    boolean createUser(User signupRequest);
}
