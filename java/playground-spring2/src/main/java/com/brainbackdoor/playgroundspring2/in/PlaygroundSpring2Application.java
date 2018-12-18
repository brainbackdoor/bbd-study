package com.brainbackdoor.playgroundspring2.in;

import com.brainbackdoor.playgroundspring2.out.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.support.GenericApplicationContext;

import java.util.function.Supplier;

@SpringBootApplication
public class PlaygroundSpring2Application {
/*
    ComponentScan을 보면,
    @AliasFor("basePackages")
    String[] value() default {};

    @AliasFor("value")
    String[] basePackages() default {};

    Class<?>[] basePackageClasses() default {};

    따라서, 이 Application을 담고있는 Package부터 ComponentScan이 된다.
    @Repository @Service @Controller @Configuration
 */
/*
    ComponentScan하는 중에,
    TypeExcludeFilter, AutoConfigurationExcludeFilter 이 두개를 필터링함
 */
    @Autowired
    MyService myService;

    public static void main(String[] args) {
//        SpringApplication.run(PlaygroundSpring2Application.class, args);
        // Instance를 만들어서 사용하는 방법
        var app = new SpringApplication(PlaygroundSpring2Application.class);
        app.addInitializers((ApplicationContextInitializer<GenericApplicationContext>) ctx -> {
            ctx.registerBean(MyService.class);

            // Functional한 방법
            // Bean Application 구동시에 성능상에 이점이 있음 (Reflection이나 Proxy를 안쓰므로..)
            // 그러나 ComponentScan을 대체하면 설정파일이 늘어날 수 있으므로 비추천
            // 별도로 Bean 등록해서 쓰는 것정도만 대체해도 괜찮을듯
            ctx.registerBean(ApplicationRunner.class, () -> (ApplicationRunner) args1 ->
                    System.out.println("Functional Bean Definition!!"));
//            ctx.registerBean(ApplicationRunner.class, new Supplier<ApplicationRunner>() {
//                @Override
//                public ApplicationRunner get() {
//                    return new ApplicationRunner() {
//                        @Override
//                        public void run(ApplicationArguments args) throws Exception {
//                            System.out.println("Functional Bean Definition!!");
//                        }
//                    };
//                }
//            });
        });

        app.run(args);
    }

}

