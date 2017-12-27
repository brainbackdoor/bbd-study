package com.educhoice.motherchoice.configuration.security.service;

import com.educhoice.motherchoice.models.nonpersistent.authorization.Token;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenStorageService {

	private final int EXPIRY_HOURS = 1;
	private static final Logger log = LoggerFactory.getLogger(TokenStorageService.class);

	public LoadingCache<String, Token> cache;

	public TokenStorageService() {
		super();
		this.cache = CacheBuilder.newBuilder().expireAfterWrite((long) EXPIRY_HOURS, TimeUnit.HOURS)
				.build(new CacheLoader<String, Token>() {
					@Override
					public Token load(String s) throws Exception {
						return new Token();
					}
				});
	}

	public void putToken(Token token) {
		this.cache.put(token.getEmail(), token);
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


	private boolean isCorrectToken(String email, String tokenValue) {
		try {
			boolean isCorrect = this.cache.get(email).isCorrectToken(tokenValue);
			if (isCorrect) {
				log.info(String.format("token info : %s, email: %s", this.cache.get(email).getTokenValue(),
						this.cache.get(email).getEmail()));
				this.certify(email);
				return isCorrect;
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return false;
	}

	public boolean verifyToken(Token token) {
        return this.isCorrectToken(token.getEmail(), token.getTokenValue());
	}

	public boolean isCertified(String email) {
	    try {
            return this.cache.get(email).isCertified();
        } catch(Exception e) {
	        log.error(e.getMessage());
        }
		return false;
	}
}
