package com.educhoice.motherchoice.configuration.security;

import com.educhoice.motherchoice.configuration.security.service.filters.FormLoginJwtAutheticationFilter;
import com.educhoice.motherchoice.configuration.security.service.filters.JwtAuthenticationFilter;
import com.educhoice.motherchoice.configuration.security.service.social.SocialLoginAuthenticationManager;
import com.educhoice.motherchoice.service.JwtIdService;
import com.educhoice.motherchoice.utils.exceptions.resolvers.security.RestSecurityEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import sun.reflect.annotation.ExceptionProxy;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private ResourceServerTokenServices tokenService;

    @Autowired
    private SocialLoginAuthenticationManager socialLoginAuthenticationManager;

    @Autowired
    private RestSecurityEntryPoint entryPoint;

    @Qualifier("accessTokenConverter")
    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    private JwtIdService jwtIdService;

    @Value("testjwtresourceid")
    private String resourceIds;

    @Value("realm")
    private String securityRealm;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(resourceIds).tokenServices(tokenService);
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .exceptionHandling()
                .authenticationEntryPoint(entryPoint);

        httpSecurity
                .requestMatchers()
                .and()
                .authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/v1/**").authenticated()
                .and()
                .addFilterBefore(new FormLoginJwtAutheticationFilter("/formlogin", socialLoginAuthenticationManager, tokenConverter, jwtIdService), BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtAuthenticationFilter("/kakao", socialLoginAuthenticationManager, tokenConverter, jwtIdService), FormLoginJwtAutheticationFilter.class);

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm)
                .and()
                .cors()
                .disable();

        httpSecurity
                .headers()
                .frameOptions()
                .disable()
                .and()
                .csrf()
                .disable();
    }


}
