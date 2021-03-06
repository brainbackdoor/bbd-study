package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.domainevents.NewCorporateAccountEvent;
import com.educhoice.motherchoice.models.persistent.Academy;
import com.educhoice.motherchoice.models.persistent.qna.Answer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.domain.DomainEvents;

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

	@OneToOne(mappedBy = "corporateAccount")
    @JsonIgnore
	private Academy academy;

	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private List<Answer> answers;

	@DomainEvents
    NewCorporateAccountEvent accountEvent() {
	    return new NewCorporateAccountEvent(this);
    }

    public CorporateAccount(String email, String password) {
        super(email, password);
    }

    public CorporateAccount(String email, String password, AccountRoles roles) {
        super(email, password, null, roles);
    }

    public CorporateAccount(String email, String password, String phoneNo, String accountName, String profileUri, AccountRoles roles) {
        super(email, password, profileUri, roles);
        this.accountName = accountName;
        this.phoneNo = phoneNo;
        this.paid = AccountRoles.isPaidTier(roles);
    }
}
