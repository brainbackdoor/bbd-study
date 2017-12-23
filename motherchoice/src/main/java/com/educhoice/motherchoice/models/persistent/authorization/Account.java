package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    private String email;
    private String password;
    private String nickname;

    private String sido;
    private String sigungu;
    private String dong;


    private boolean marketingAllowed;


}
