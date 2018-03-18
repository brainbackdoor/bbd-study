package com.educhoice.motherchoice.utils.aws;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import org.springframework.beans.factory.annotation.Value;

public class AWSInstanceService {

    @Value(value = "${spring.profiles.active}")
    private String activeProfile;

    public AWSCredentialsProvider returnProfileCredentials() {
        if("local".equals(this.activeProfile)) {
            return new ProfileCredentialsProvider();
        }
        return InstanceProfileCredentialsProvider.getInstance();
    }
}
