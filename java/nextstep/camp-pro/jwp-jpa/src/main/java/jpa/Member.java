package jpa;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Favorite> favorites = new ArrayList();

    public Member() {
    }

    public Member(String email) {
        this.email = email;
    }

    public void addFavorite(Favorite favorite) {
        favorites.add(favorite);
        favorite.addMember(this);
    }

    public List<Favorite> findFavorites() {
        return Collections.unmodifiableList(favorites);
    }
}
