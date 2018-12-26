package com.brainbackdoor.playgroundspring2.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class BookService {
    @Autowired
    BookRepository myBookRepository; //이름이 같을 경우도 찾아내서 매핑해준다
/*
     InitializingBean 이전에 beanPostProcessor에 의해
     AutowiredAnnotationBeanPostProcessor가 동작해서
     Autowried Annotation을 처리해준다(빈을 찾아서 처리해준다)

     Runner는 SpringBoot가 지원해주는 Interface라 Application이 모두 구동된 이후 일을 하나,
     PostConstruct는 12번 단계에서 일을 하므로 출력을 하는 시점이 좀 다르다.

     11. postProcessBeforeInitialization methods of BeanPostProcessors
     12. InitializingBean's afterPropertiesSet
     13. a custom init-method definition
     14. postProcessAfterInitialization methods of BeanPostProcessors

     BeanFactory가 BeanPostProcessor Bean들을 찾음
     (그 중에 AutowiredAnnotationBeanPostProcessor extends BeanPostProcessor도 있음)
     AutowiredAnnotationBeanPostProcessor는 Bean으로 등록되어 있음
 */
    @PostConstruct
    public void setUp() {
        System.out.println(myBookRepository.getClass());
    }
}
