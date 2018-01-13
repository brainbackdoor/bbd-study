package com.educhoice.motherchoice.models.persistent;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Image {

    public enum ImageDisplayTypes {
        FULL_SIZED(0),
        MID_SIZED(1),
        THUMB(2);

        private int code;

        ImageDisplayTypes(int code) {
            this.code = code;
        }

        @JsonValue
        public int getCode() {
            return this.code;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private long imageId;

    @ManyToOne
    @JsonIgnore
    private Academy academy;

    @Enumerated(value = EnumType.STRING)
    private ImageDisplayTypes imageDisplayTypes;

    private String uri;
}
