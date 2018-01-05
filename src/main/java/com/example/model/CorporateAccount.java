package com.example.model;

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
	private List<DummySearchAcademy> ownedAcademies;

}
