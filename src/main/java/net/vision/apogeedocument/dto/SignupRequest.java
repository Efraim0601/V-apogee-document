package net.vision.apogeedocument.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignupRequest {
    private Integer numeroempl;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer numero;

}
