package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.authorization.Wonjang;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface WonjangRepository extends CrudRepository<Wonjang, Long> {

    public Optional<Wonjang> findByEmail(String email);
}
