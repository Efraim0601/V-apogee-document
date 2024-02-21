package net.vision.apogeedocument.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.vision.apogeedocument.enumerable.RoleType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "ROLE")
@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private RoleType wording;
}
