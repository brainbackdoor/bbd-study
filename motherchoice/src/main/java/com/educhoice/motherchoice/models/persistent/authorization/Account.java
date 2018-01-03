package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;
import com.educhoice.motherchoice.utils.converter.MemberAddrToStringConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account extends BasicAccount{

    private String nickname;

    @Column
    @Convert(converter = MemberAddrToStringConverter.class)
    private MemberAddress memberAddress;

    private boolean marketingAllowed;

    @Override
    public String toString() {
        return "Account{" +
                "nickname='" + nickname + '\'' +
                ", memberAddress=" + memberAddress +
                ", marketingAllowed=" + marketingAllowed +
                '}';
    }
}
