package com.brainbackdoor.playgroundspring3;

import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

/*
    profile 표현식 : !(not), &(and), |(or)
 */
@Repository
@Profile("!prod")
public class TestBookRepository implements BookRepository {
}
