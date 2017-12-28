package com.educhoice.motherchoice.models.persistent.qna;

import com.educhoice.motherchoice.models.persistent.authorization.BasicAccount;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyId")
    private BasicAccount account;


    private String content;


}
