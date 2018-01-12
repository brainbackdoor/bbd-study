package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.Academy;
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
	private boolean paid;

	@JsonIgnore
	private String ownerName;

	@OneToOne
	@JoinColumn(name = "academyId")
	private Academy academy;

    public CorporateAccount(String email, String password) {
        super(email, password);
    }

    public CorporateAccount(String email, String password, String ownerName, String phoneNo, String profileUri, AccountRoles roles) {
        super(email, password, profileUri, roles);
        this.paid = AccountRoles.isPaidTier(roles);
    }
}
