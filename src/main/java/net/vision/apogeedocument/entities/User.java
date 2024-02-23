package net.vision.apogeedocument.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
 @Getter
@Setter
@Table(name = "USER")
public class User {

    @Id
    private Integer numeroempl;
    private String firstName;
    private String lastName;
    private String email;
    private Integer numero;
    @Column(name = "password")
    private String password;
    private boolean isActive = false;

    @OneToOne(cascade=CascadeType.ALL)
    private Role wording;
}
