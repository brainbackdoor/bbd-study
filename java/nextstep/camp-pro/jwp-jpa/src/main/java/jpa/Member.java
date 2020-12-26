package jpa;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Member extends BaseEntity {
    private Integer age;

    @Column(nullable = false, unique = true)
    private String email;
    private String password;

    public Member() {
    }

    public Member(String email) {
        this.email = email;
    }

}
