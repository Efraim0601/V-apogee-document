package net.vision.apogeedocument.repositories;

import net.vision.apogeedocument.entities.Validation;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
    void deleteAllByExpireBefore(Instant now);
}
