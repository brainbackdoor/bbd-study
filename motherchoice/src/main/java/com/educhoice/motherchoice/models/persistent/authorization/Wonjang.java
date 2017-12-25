package com.educhoice.motherchoice.models.persistent.authorization;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Wonjang extends BasicAccount {

	private String phoneNo;
	private String ownerName;

}
