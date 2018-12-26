package com.educhoice.motherchoice.models.persistent.repositories.notifications;

import com.educhoice.motherchoice.models.persistent.notifications.NewQuestionStore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface NewQuestionStoreRepository extends MongoRepository<NewQuestionStore, Long> {

    Optional<NewQuestionStore> findByCorporateAccountId(long corporateAccountId);

}
