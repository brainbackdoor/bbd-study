package com.educhoice.motherchoice.models.persistent.repositories;

import com.educhoice.motherchoice.models.persistent.qna.Question;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Long> {

}
