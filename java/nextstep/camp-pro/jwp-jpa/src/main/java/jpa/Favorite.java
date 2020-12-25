package jpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToOne
    private Station upLine;

    @OneToOne
    private Station downLine;

    public Favorite() {
    }

    public Favorite(Station upLine, Station downLine) {
        this.upLine = upLine;
        this.downLine = downLine;
    }

    public void addMember(Member member) {
        this.member = member;
    }
}
