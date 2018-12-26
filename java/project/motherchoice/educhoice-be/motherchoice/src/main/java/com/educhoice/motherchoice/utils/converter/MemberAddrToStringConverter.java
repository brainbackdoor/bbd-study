package com.educhoice.motherchoice.utils.converter;

import com.educhoice.motherchoice.models.persistent.geolocation.MemberAddress;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;

@Converter
public class MemberAddrToStringConverter implements AttributeConverter<MemberAddress, String> {

    @Override
    public String convertToDatabaseColumn(MemberAddress memberAddress) {
        StringBuilder sb = new StringBuilder();

        sb.append(memberAddress.getSido() + " ");
        sb.append(memberAddress.getSigungu() + " ");
        sb.append(memberAddress.getDong());

        return sb.toString();
    }

    @Override
    public MemberAddress convertToEntityAttribute(String s) {
        List<String> addrinfo = Arrays.asList(s.split(" "));
        MemberAddress address = new MemberAddress();

        address.setSido(addrinfo.get(0));
        address.setSigungu(addrinfo.get(1));
        address.setDong(addrinfo.get(2));

        return address;
    }
}
