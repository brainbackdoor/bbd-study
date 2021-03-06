package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.configuration.security.event.EmailVerifiedEvent;
import com.educhoice.motherchoice.configuration.security.event.publisher.EmailVerifiedEventPublisher;
import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailTokenService {

    private final int RANDOM_STRING_LENGTH = 32;
	private final int EXPIRY_HOURS = 1;
	private static final Logger log = LoggerFactory.getLogger(EmailTokenService.class);

	@Autowired
    private RandomStringUtils randomStringUtils;

	@Autowired
    private EmailVerifiedEventPublisher eventPublisher;

	public LoadingCache<String, Token> cache;

	public EmailTokenService() {
		super();
		this.cache = CacheBuilder.newBuilder().expireAfterWrite((long) EXPIRY_HOURS, TimeUnit.HOURS)
				.build(new CacheLoader<String, Token>() {
					@Override
					public Token load(String s) throws Exception {
						return new Token();
					}
				});
	}

	public Token generateToken(String email) {
	    return putToken(email);
    }

	public void putToken(Token token) {
		this.cache.put(token.getEmail(), token);
	}

	private Token putToken(String email) {
	    Token token = new Token(email, randomStringUtils.generateRandomString(RANDOM_STRING_LENGTH));
	    this.cache.put(email, token);

	    return token;
    }

    public boolean verifyEmail(Token token) {
        return this.isValidToken(token.getEmail(), token.getTokenValue());
    }

    public boolean isCertified(String email) {
        try {
            return this.cache.get(email).isCertified();
        } catch(Exception e) {
            log.error(e.getMessage());
        }
        return false;
    }

	private void certify(String email) {
	    Token token = null;
		try {
			token = this.cache.get(email);
			this.cache.invalidate(email);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		token.setCertified(true);
		this.cache.put(token.getEmail(), token);
	}

	private boolean isValidToken(String email, String tokenValue) {
		try {
			boolean isCorrect = this.cache.get(email).isCorrectToken(tokenValue);
			if (isCorrect) {
				log.info(String.format("token info : %s, loginId: %s", this.cache.get(email).getTokenValue(),
						this.cache.get(email).getEmail()));
				this.certify(email);
				eventPublisher.publish(new EmailVerifiedEvent(this.cache.get(email)));
				return isCorrect;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}


}
