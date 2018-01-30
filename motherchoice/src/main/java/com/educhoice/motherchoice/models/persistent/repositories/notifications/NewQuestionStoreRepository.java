package com.educhoice.motherchoice.models.persistent.repositories.notifications;

import com.educhoice.motherchoice.models.persistent.notifications.NewQuestionStore;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NewQuestionStoreRepository extends MongoRepository<NewQuestionStore, Long> {


}
