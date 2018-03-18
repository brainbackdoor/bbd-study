package com.educhoice.motherchoice.utils.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;
import javax.persistence.Converter;
import java.time.Instant;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Converter
public class LocalDateTimeConverter implements AttributeConverter<LocalDateTime, Date>{

    @Override
    public Date convertToDatabaseColumn(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    @Override
    public LocalDateTime convertToEntityAttribute(Date date) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
    }
}
