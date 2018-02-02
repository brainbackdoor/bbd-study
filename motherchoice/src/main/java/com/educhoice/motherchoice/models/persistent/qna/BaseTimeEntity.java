package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.utils.converter.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class BaseTimeEntity {

    @CreatedDate
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonIgnore
    private LocalDateTime createdTime;

    @LastModifiedDate
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonIgnore
    private LocalDateTime updatedTime;

    @JsonIgnore
    public String getFormattedCreatedTime() {
        return formatDateToString(createdTime, "yyyy.MM.dd");
    }

    @JsonProperty(value = "createdTime")
    public String getCreatedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        return this.createdTime.format(formatter);
    }

    private String formatDateToString(LocalDateTime localDateTime, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return localDateTime.format(formatter);
    }

}
