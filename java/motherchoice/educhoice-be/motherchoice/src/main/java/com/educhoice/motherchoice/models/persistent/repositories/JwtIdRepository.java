package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.authorization.JwtId;
import org.springframework.data.mongodb.repository.MongoRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface JwtIdRepository extends MongoRepository<JwtId, Long> {

    Optional<JwtId> findByBearerName(String bearerName);
    Optional<JwtId> findByJti(String jti);
}
