package bbd.model;

public class AcademyUser {
	private String emailId;
	private String password;
	private String academyName;
	private String ownerName;
	private String academyNum;
	private String area;
	public AcademyUser(String emailId, String password, String academyName) {
		this.emailId = emailId;
		this.password = password;
		this.academyName = academyName;
	}
	public AcademyUser(String emailId, String password, String academyName, String academyNum, String ownerName) {
		this.emailId = emailId;
		this.password = password;
		this.academyName = academyName;
		this.ownerName = ownerName;
		this.academyNum = academyNum;
	}
	public AcademyUser(String emailId, String password, String academyName, String academyNum, String ownerName, String area) {
		this.emailId = emailId;
		this.password = password;
		this.academyName = academyName;
		this.ownerName = ownerName;
		this.academyNum = academyNum;
		this.area = area;
	}
	public void update(AcademyUser updateAcademyUser) {
		this.password = updateAcademyUser.password;
		this.academyName = updateAcademyUser.academyName;
		this.area = updateAcademyUser.area;
	}


	public String getAcademyName() {
		return academyName;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public String getEmailId() {
		return emailId;
	}

	public String getPassword() {
		return password;
	}

	public String getArea() {
		return area;
	}
	public String getAcademyNum() {
		return academyNum;
	}

}
