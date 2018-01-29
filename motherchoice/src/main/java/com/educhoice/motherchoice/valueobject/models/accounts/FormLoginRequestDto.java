package com.educhoice.motherchoice.valueobject.models.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormLoginRequestDto {

    @JsonProperty(value = "id")
    private String loginId;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "time")
    private String timestamp;

    public LocalTime getTimeStamp() {
        return LocalTime.parse(this.timestamp);
    }

}
