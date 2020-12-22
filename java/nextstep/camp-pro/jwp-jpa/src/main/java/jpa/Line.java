package jpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Line {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;
    private String color;

    @Column(unique = true)
    private String name;


}
