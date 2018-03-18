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
		
        registry.addViewController("/lotto").setViewName("lotto/form");
        registry.addViewController("/pages/lotto").setViewName("pages/lotto");
        registry.addViewController("/pages/login").setViewName("pages/login");
        registry.addViewController("/pages/manual").setViewName("pages/manual");
        registry.addViewController("/pages/encharge").setViewName("pages/encharge");
        registry.addViewController("/users/form").setViewName("user/form");
        registry.addViewController("/user/updateForm").setViewName("user/updateForm");
        registry.addViewController("/users/login").setViewName("user/login");
        registry.addViewController("/user/login_failed").setViewName("user/login_failed");
    }
}
