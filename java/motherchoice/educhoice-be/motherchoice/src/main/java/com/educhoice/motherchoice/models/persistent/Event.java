package com.educhoice.motherchoice.models.persistent;

import com.educhoice.motherchoice.models.persistent.qna.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Event extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long eventId;

    @NotNull
    private String title;

    @Column(columnDefinition = "TEXT NOT NULL")
    private String content;

    @JsonProperty(value = "createdTime")
    public String getCreatedTime() {
        return super.getCreatedTime();
    }

}
