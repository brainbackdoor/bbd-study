package com.educhoice.motherchoice.configuration.security.service.filters;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.configuration.security.service.IntegratedUserQueryService;
import com.educhoice.motherchoice.configuration.security.service.social.SocialLoginAuthenticationManager;
import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.configuration.security.service.social.token.SocialLoginAccessToken;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.Account;
import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import com.educhoice.motherchoice.models.persistent.repositories.AccountRepository;
import com.educhoice.motherchoice.models.persistent.repositories.CorporateAccountRepository;
import com.educhoice.motherchoice.service.JwtIdService;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class JwtAuthenticationFilterTest {

    private static final Logger log = LoggerFactory.getLogger(JwtAuthenticationFilterTest.class);

    @Autowired
    private SocialLoginAuthenticationManager authenticationManager;

    @Qualifier("accessTokenConverter")
    @Autowired
    private JwtAccessTokenConverter tokenConverter;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private JwtIdService jwtIdService;

    private JwtAuthenticationFilter filter;
    private Account account;
    private HttpServletRequest req;
    private SocialAuthinfoDto dto;

    @Before
    public void setUp() {
        this.filter = new JwtAuthenticationFilter("/hello", this.authenticationManager, this.tokenConverter, this.jwtIdService);

        this.account = new Account("정휘준", "1234", "봄이네집", null, null, true, BasicAccount.AccountRoles.UNPAID_USER, SocialSigninProviders.KAKAO, 705692990L, "nL-jMA6reSagOq23xuheawJRNbF6qgMCAyehZwo8BRIAAAFhIbvBxw", null);
        accountRepository.save(this.account);

        this.dto = new SocialAuthinfoDto();
        this.dto.setProvider(SocialSigninProviders.KAKAO);
        this.dto.setAccessToken("VvSLAjeK2Vg5gY-a-_8LPH8GOY1h32_CJW1h5Qo8BZUAAAFhIeXjnQ");
        this.dto.setRefreshToken(null);

    }

    @Test
    public void 유저정보_인증테스트() throws Exception {
        OAuth2Authentication authentication = (OAuth2Authentication) this.filter.authenticateFromDto(this.dto);
        SecurityAccount account = (SecurityAccount) authentication.getPrincipal();

        assertEquals("정휘준", account.getBasicAccount().getLoginId());
        log.debug(this.filter.generateJwtString(new SocialLoginAccessToken(account, jwtIdService.generateJti(account.getUsername())), authentication));
    }
}