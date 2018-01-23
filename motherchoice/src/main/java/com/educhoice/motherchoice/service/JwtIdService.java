package com.educhoice.motherchoice.service;

import com.educhoice.motherchoice.models.persistent.authorization.JwtId;
import com.educhoice.motherchoice.models.persistent.repositories.JwtIdRepository;
import com.educhoice.motherchoice.utils.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtIdService {

//    @Autowired
//    private JwtIdRepository jwtIdRepository;

    @Autowired
    private RandomStringUtils randomStringUtils;

    public JwtId generateJti(String bearerName) {
        //TODO write JTI generation logic.
        String jti = randomStringUtils.generateRandomString(16);
//        while(!jwtIdRepository.findByJti(jti).isPresent()) {
//            jti = randomStringUtils.generateRandomString(16);
//        }

        return JwtId.builder()
                .bearerName(bearerName)
                .blackListed(false)
                .jti(jti)
                .id(System.currentTimeMillis())
                .build();
    }

}
