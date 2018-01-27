package com.educhoice.motherchoice.utils.aws;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.educhoice.motherchoice.models.persistent.authorization.CorporateAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class S3ControlService {

    private static final Logger log = LoggerFactory.getLogger(S3ControlService.class);

    private final String BUCKETNAME_PREFIX = "educhoice-member-static-";

    @Autowired
    private AWSInstanceService awsInstanceService;

    private AmazonS3 client;

    public void init() {
        this.client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.AP_NORTHEAST_2)
                .withCredentials(awsInstanceService.returnProfileCredentials())
                .build();
    }


    public void generateBucket(CorporateAccount account) {
        log.debug("is credentials service autowired? : {}" , awsInstanceService != null);
        client.createBucket(generateBucketName(account));
    }

    private String generateBucketName(CorporateAccount account) {
        StringBuilder builder = new StringBuilder();

        builder.append(BUCKETNAME_PREFIX);
        builder.append(account.getAccountName());

        return builder.toString();
    }

}
