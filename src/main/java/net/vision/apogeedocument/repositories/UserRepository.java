package net.vision.apogeedocument.repositories;

import net.vision.apogeedocument.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    boolean existsByEmail(String email);


    Optional<User> findByEmail(String email);
    Optional<User> findByNumeroempl(Integer id);
}
