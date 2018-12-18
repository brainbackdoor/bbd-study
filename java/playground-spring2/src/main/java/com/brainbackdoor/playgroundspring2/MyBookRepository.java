package com.brainbackdoor.playgroundspring2;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository @Primary
public class MyBookRepository implements BookRepository{
}
