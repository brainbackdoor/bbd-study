package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.qna.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateAccount extends BasicAccount {

	private String phoneNo;

	@JsonIgnore
	private boolean paid;

	private String ownerName;

	@OneToOne
	@JoinColumn(name = "academyId")
    @JsonIgnore
	private Academy academy;

	@OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;

    public CorporateAccount(String email, String password) {
        super(email, password);
    }

    public CorporateAccount(String email, String password, String ownerName, String phoneNo, String profileUri, AccountRoles roles) {
        super(email, password, profileUri, roles);
        this.paid = AccountRoles.isPaidTier(roles);
    }
}
