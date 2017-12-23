package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Generated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter
@Setter
public class BasicAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long accountId;

    @NotNull
    private String email;

    @NotNull
    private String password;

    public BasicAccount() {}

    public BasicAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }


}
