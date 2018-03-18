package com.educhoice.motherchoice.models.persistent.geolocation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberAddress {

	private String sido;
	private String sigungu;
	private String dong;

	public MemberAddress() {
	}

	public MemberAddress(String sido, String sigungu, String dong) {
		this.sido = sido;
		this.sigungu = sigungu;
		this.dong = dong;
	}

	@Override
	public String toString() {
		return "MemberAddress{" + "sido='" + sido + '\'' + ", sigungu='" + sigungu + '\'' + ", dong='" + dong + '\''
				+ '}';
	}
}
