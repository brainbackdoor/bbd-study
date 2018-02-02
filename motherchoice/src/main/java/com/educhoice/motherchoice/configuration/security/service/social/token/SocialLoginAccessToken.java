package com.educhoice.motherchoice.configuration.security.service.social.token;

import com.educhoice.motherchoice.configuration.security.entity.IntegratedUserSigninToken;
import com.educhoice.motherchoice.models.nonpersistent.authorization.SecurityAccount;
import com.educhoice.motherchoice.models.persistent.authorization.JwtId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Set;

public class SocialLoginAccessToken implements OAuth2AccessToken {

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private int ttl;

    @JsonProperty("scope")
    private Set<String> scopes;

    @JsonProperty("jti")
    private String value;

    private Map<String, Object> additionalInformation;
    private OAuth2RefreshToken refreshToken;

    @JsonIgnore
    private long issuedDate;

    public SocialLoginAccessToken(SecurityAccount account, JwtId id) {

        Set<String> scopes = Sets.newHashSet();
        scopes.add("read");
        scopes.add("write");

        this.accessToken = null;
        this.tokenType = "bearer";
        this.ttl = 43200000;  //expiry time in milliseconds(ms) e.g. 43200000L = 43200 sec = 720 min = 12 hrs.
        this.scopes = scopes;
        this.value = account.getBasicAccount().getLoginId();
        this.issuedDate = System.currentTimeMillis();
        this.additionalInformation = Maps.newLinkedHashMap();
        this.additionalInformation.put("jti", id.getJti());
        this.additionalInformation.put("USER_UID", account.getBasicAccount().getAccountId());
        this.additionalInformation.put("PARSED_AUTHORITIES", account.getBasicAccount().getRoles().getSymbol());

        if(account.getBasicAccount().getSocialProvider() != null) {
            this.additionalInformation.put("SOCIAL_PROVIDER", account.getBasicAccount().getSocialProvider().getProviderName());
        }

        this.refreshToken = null;

    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }

    @Override
    public Set<String> getScope() {
        return this.scopes;
    }

    @Override
    public OAuth2RefreshToken getRefreshToken() {
        return this.refreshToken;
    }

    @Override
    public String getTokenType() {
        return this.tokenType;
    }

    @Override
    public boolean isExpired() {
        return this.checkExpiry(this.ttl, this.issuedDate);
    }

    @Override
    public Date getExpiration() {
        return new Date(this.issuedDate + this.ttl);
    }

    @Override
    public int getExpiresIn() {
        return this.ttl;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private boolean checkExpiry(long ttl, long issuedDate) {
        long today = System.currentTimeMillis();

        return today - issuedDate > ttl;
    }
}
