package com.educhoice.motherchoice.models.persistent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.websocket.server.ServerEndpoint;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademyResource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String fileName;
    private String href;
}
