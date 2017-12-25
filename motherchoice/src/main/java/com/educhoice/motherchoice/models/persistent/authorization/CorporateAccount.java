package com.educhoice.motherchoice.models.persistent.authorization;

import com.educhoice.motherchoice.models.persistent.Academy;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class CorporateAccount extends BasicAccount {

	private String phoneNo;
	private String ownerName;

	@OneToMany
	private List<Academy> ownedAcademies;

}
