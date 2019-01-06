package com.brainbackdoor.playgroundjpa01;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Account {
    @Id
    @GeneratedValue
    private Long id;

//    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @Temporal(TemporalType.TIME)
    private Date created = new Date();

    private String yes;

    @Transient
    private String no;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "street", column = @Column(name = "homeAddress"))
    })
    private Address homeAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
