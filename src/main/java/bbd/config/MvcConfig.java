package bbd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
        registry.addViewController("/pages/login").setViewName("pages/login");
        registry.addViewController("/pages/index").setViewName("pages/index");
        registry.addViewController("/pages/parentJoinForm").setViewName("pages/parentJoinForm");
        registry.addViewController("/pages/parentJoinAuthWaitForm").setViewName("pages/parentJoinAuthWaitForm");

        
    }
}
