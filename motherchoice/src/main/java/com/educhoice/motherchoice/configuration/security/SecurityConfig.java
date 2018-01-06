package com.educhoice.motherchoice.configuration.security;

import com.educhoice.motherchoice.configuration.security.service.OAuthClientResource;
import com.educhoice.motherchoice.configuration.security.service.OAuthUserTokenService;
import com.educhoice.motherchoice.configuration.security.service.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.IntegratedOAuthSigninSuccessHandler;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.filter.CompositeFilter;

import javax.servlet.Filter;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableOAuth2Client
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Qualifier("oauth2ClientContext")
    @Autowired
    OAuth2ClientContext clientContext;


    @Override
    public void configure(HttpSecurity security) throws Exception {
        security
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .and()
                .addFilterBefore(generateFilterSet(), BasicAuthenticationFilter.class);

        security
                .csrf().disable();

        security
                .headers().frameOptions().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(filter);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    @ConfigurationProperties("naver")
    public OAuthClientResource naverResources() {
        return new OAuthClientResource();
    }

    @Bean
    @ConfigurationProperties("kakao")
    public OAuthClientResource kakaoResources() {
        return new OAuthClientResource();
    }

    private Filter generateFilterSet() {
        CompositeFilter filter = new CompositeFilter();

        List<Filter> filters = Lists.newArrayList();
        filters.add(ssoFilter(naverResources(), "/login/naver", SocialSigninProviders.NAVER));
        filters.add(ssoFilter(kakaoResources(), "/login/kakao", SocialSigninProviders.KAKAO));
        filter.setFilters(filters);

        return filter;
    }

    private Filter ssoFilter(OAuthClientResource resource, String path, SocialSigninProviders providers) {
        OAuth2ClientAuthenticationProcessingFilter filter = new OAuth2ClientAuthenticationProcessingFilter(path);
        OAuth2RestTemplate template = new OAuth2RestTemplate(resource.getClientDetails(), clientContext);

        filter.setRestTemplate(template);
        filter.setTokenServices(new OAuthUserTokenService(resource));
        filter.setAuthenticationSuccessHandler(new IntegratedOAuthSigninSuccessHandler("/", providers));

        return filter;
    }
}
