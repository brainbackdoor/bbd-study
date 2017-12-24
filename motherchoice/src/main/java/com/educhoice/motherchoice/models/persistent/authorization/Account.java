package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;
import com.educhoice.motherchoice.utils.converter.MemberAddrToStringConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class Account extends BasicAccount{

    private String nickname;

    @Column
    @Convert(converter = MemberAddrToStringConverter.class)
    private MemberAddress memberAddress;


    private boolean marketingAllowed;


    public Account(String email, String password, String nickname, MemberAddress memberAddress) {
        super(email, password);
        this.nickname = nickname;
        this.memberAddress = memberAddress;
    }

    public Account() {}

    @Override
    public String toString() {
        return "Account{" +
                "nickname='" + nickname + '\'' +
                ", memberAddress=" + memberAddress +
                ", marketingAllowed=" + marketingAllowed +
                '}';
    }
}
