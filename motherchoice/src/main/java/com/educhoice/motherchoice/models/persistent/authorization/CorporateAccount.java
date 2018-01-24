package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.qna.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CorporateAccount extends BasicAccount {

    @JsonIgnore
	private String phoneNo;

	@JsonIgnore
	private boolean paid;

	private String accountName;

	@OneToOne
	@JoinColumn(name = "academyId")
    @JsonIgnore
	private Academy academy;

	@OneToMany(fetch = FetchType.LAZY)
    private List<Answer> answers;

    public CorporateAccount(String email, String password) {
        super(email, password);
    }

    public CorporateAccount(String email, String password, AccountRoles roles) {
        super(email, password, null, roles);
    }

    public CorporateAccount(String email, String password, String phoneNo, String accountName, String profileUri, AccountRoles roles) {
        super(email, password, profileUri, roles);
        this.phoneNo = phoneNo;
        this.paid = AccountRoles.isPaidTier(roles);
    }
}
