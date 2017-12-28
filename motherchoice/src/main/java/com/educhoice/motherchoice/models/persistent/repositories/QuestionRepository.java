package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.qna.Question;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface QuestionRepository extends PagingAndSortingRepository<Question, Long> {
}
