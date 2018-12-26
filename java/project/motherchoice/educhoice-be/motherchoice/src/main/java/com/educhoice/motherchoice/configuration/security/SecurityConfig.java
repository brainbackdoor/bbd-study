package com.educhoice.motherchoice.configuration.security;

import com.educhoice.motherchoice.configuration.security.service.AccountDetailsService;
import com.educhoice.motherchoice.configuration.security.service.filters.AuthenticationExceptionHandlerFilter;
import com.educhoice.motherchoice.configuration.security.service.filters.FormLoginJwtAutheticationFilter;
import com.educhoice.motherchoice.configuration.security.service.filters.JwtAuthenticationFilter;
import com.educhoice.motherchoice.configuration.security.service.social.IntegratedLoginAuthenticationManager;
import com.educhoice.motherchoice.service.JwtIdService;
import com.educhoice.motherchoice.utils.exceptions.resolvers.security.RestSecurityEntryPoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${security.signing-key}")
    private String signature;

    @Autowired
    private AccountDetailsService accountDetailsService;

    @Autowired
    private ResourceServerTokenServices tokenService;

    @Autowired
    private IntegratedLoginAuthenticationManager integratedLoginAuthenticationManager;

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
    protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(this.accountDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);

        return defaultTokenServices;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true);
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", configuration);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE + 1);

        return bean;
    }

    @Bean
    public FilterRegistrationBean securityExceptionFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new AuthenticationExceptionHandlerFilter());
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return bean;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(signature);
        return converter;
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
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/h2-console/**").permitAll()
//                .antMatchers("/api/**").authenticated()
                .antMatchers("/api/**").permitAll()
                .and()
                .addFilterAfter(new FormLoginJwtAutheticationFilter("/formlogin", integratedLoginAuthenticationManager, tokenConverter, jwtIdService), CorsFilter.class)
                .addFilterAfter(new JwtAuthenticationFilter("/tokenauth", integratedLoginAuthenticationManager, tokenConverter, jwtIdService), FormLoginJwtAutheticationFilter.class);

        httpSecurity
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .httpBasic()
                .realmName(securityRealm);

        httpSecurity
                .headers()
                .frameOptions()
                .disable()
                .and()
                .csrf()
                .disable();
    }

}
