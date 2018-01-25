package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.entity.UserJoinRequest;
import com.educhoice.motherchoice.configuration.security.service.social.SocialLoginAuthenticationManager;
import com.educhoice.motherchoice.configuration.security.service.social.SocialSigninProviders;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import com.educhoice.motherchoice.models.persistent.geolocation.AcademyAddress;
import com.educhoice.motherchoice.valueobject.models.academies.NewAcademyDto;
import com.educhoice.motherchoice.valueobject.models.accounts.CorporateAccountJoinDto;
import com.educhoice.motherchoice.valueobject.models.accounts.SocialAuthinfoDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserJoinServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserJoinServiceTest.class);

    private UserJoinRequest request;
    private SocialAuthinfoDto dto;

    @Autowired
    private UserJoinService service;

    @Autowired
    private IntegratedUserQueryService queryService;

    @Before
    public void setUp() {
        this.request = UserJoinRequest.builder()
                .requestType(UserJoinRequest.JoinRequestType.ACADEMY)
                .accountInfo(new CorporateAccountJoinDto("pobi@pobiworld.com", "1234", "01012345678", "박재성", new NewAcademyDto("포비학원", "박재성", "07012345678", AcademyAddress.builder().address("경기도 김포시 유현로 19").sido("경기도").sigungu("김포시").zonecode("10120").build())))
                .build();

        this.dto = new SocialAuthinfoDto();
        this.dto.setAccessToken("9_XRWAKrcDWTZnsKyoqbNRn89rFLR4ZKTRuP-AopdaYAAAFhLQ8fzw");
        this.dto.setProvider(SocialSigninProviders.KAKAO);

        this.request.setSocialAuthinfoDto(this.dto);
    }

    @Test
    @Transactional
    public void DTO기반_회원가입테스트() {
        this.service.joinAccount(this.request);
        CorporateAccount account = (CorporateAccount)this.queryService.loadByEmail("pobi@pobiworld.com");
        log.debug("encrypted password is : {}", account.getPassword());

        assertThat(account.getLoginId(), is("pobi@pobiworld.com"));
        assertThat(account.getAccountName(), is("박재성"));
        assertThat(account.getAcademy().getAcademyName(), is("포비학원"));
        assertThat(account.getAcademy().getAddress().getSido(), is("경기도"));
    }

    @Test
    @Transactional
    public void 소셜_회원가입테스트() {
        this.service.joinRequestSocial(this.request);

        CorporateAccount account = (CorporateAccount)this.queryService.loadByEmail("wheejuni@gmail.com");
        assertThat(account.getAccountName(), is("박재성"));
        assertThat(account.getSocialId(), is(705692990L));

        CorporateAccount accountBySocialId = (CorporateAccount)this.queryService.loadBySocialId(705692990L, SocialSigninProviders.KAKAO);
        assertThat(accountBySocialId.getAccountName(), is("박재성"));

    }

    @Test
    public void DTO_마샬링테스트() throws Exception {
        log.debug(new ObjectMapper().writeValueAsString(this.request));
    }


}