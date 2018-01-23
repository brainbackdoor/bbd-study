package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class JwtId {

    @Id
    private long id;

    private String bearerName;
    private String jti;
    private boolean blackListed;
}
