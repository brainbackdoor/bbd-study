package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;
import com.educhoice.motherchoice.utils.converter.MemberAddrToStringConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter

public class Account extends BasicAccount{

    private String nickname;

//    @Column
//    @Convert(converter = MemberAddrToStringConverter.class)
//    private MemberAddress memberAddress;
    private String memberAddress;

    private boolean marketingAllowed;

    public Account(String username, String password, String nickname, String memberAddress, boolean marketingAllowed, AccountRoles roles) {
        super(username, password, null, roles);
        this.nickname = nickname;
        this.memberAddress = memberAddress;
        this.marketingAllowed = marketingAllowed;
    }

    @Override
    public String toString() {
        return "Account{" +
                "nickname='" + nickname + '\'' +
                ", memberAddress=" + memberAddress +
                ", marketingAllowed=" + marketingAllowed +
                '}';
    }
}
