package com.brainbackdoor.playground.ioc1_1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class BookServiceTest {

    /*
        직접 주입
        Spring 초기에는 xml로 주입하는 것이 주류였지만,
        오늘날에는 Annotation을 사용하여 POJO객체를 빈에 등록하여 손쉽게 사용

        최상위에는 BeanFactory
        https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/BeanFactory.html

        Bean Life cycle
        1. BeanNameAware's setBeanName
        2. BeanClassLoaderAware's setBeanClassLoader
        3. BeanFactoryAware's setBeanFactory
        4. EnvironmentAware's setEnvironment
        5. EmbeddedValueResolverAware's setEmbeddedValueResolver
        6. ResourceLoaderAware's setResourceLoader (only applicable when running in an application context)
        7. ApplicationEventPublisherAware's setApplicationEventPublisher (only applicable when running in an application context)
        8. MessageSourceAware's setMessageSource (only applicable when running in an application context)
        9. ApplicationContextAware's setApplicationContext (only applicable when running in an application context)
        10. ServletContextAware's setServletContext (only applicable when running in a web application context)
        11. postProcessBeforeInitialization methods of BeanPostProcessors
        12. InitializingBean's afterPropertiesSet
        13. a custom init-method definition
        14. postProcessAfterInitialization methods of BeanPostProcessors

        Bean이란 IoC 컨테이너가 관리하는 객체

        Book, BookStatus는 JavaBean
        BookRepository, BookService는 Bean이 맞다.

        의존성 주입을 하고 싶으면 빈이 되어야 한다.
        Scope 때문이기도 하다. (Singleton으로 객체를 관리하고 싶을때도 Bean에 등록)
        Singleton <-> Prototype

        DB등과 연결되는 Repository의 경우 비용이 비싸니, Signleton으로 사용하는 것이 좋다.

        Lifecycle interface 지원(@PostCounstruct)

        ApplicationContext
     */
    @Mock
    BookRepository bookRepository;

    @Test
    public void save() {
        Book book = new Book();

//        BookRepository bookRepository = new BookRepository();
        when(bookRepository.save(book)).thenReturn(book);
        BookService bookService = new BookService(bookRepository);
        Book result = bookService.save(book);

        assertThat(book.getCreated()).isNotNull();
        assertThat(book.getStatus()).isEqualTo(BookStatus.DRAFT);
        assertThat(result).isNotNull();
    }
}