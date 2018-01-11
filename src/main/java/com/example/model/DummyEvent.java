package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DummyEvent {
	long eventId;
    String eventTitle;

    String eventContent;
    String created;
    boolean newFlag;
}
