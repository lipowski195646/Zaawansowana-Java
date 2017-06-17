package entities;

import java.util.Date;

import superclasses.EntityRef;

public class RefCompany extends EntityRef {
	private String code;
	private Date registered;
	private String address;
	private String phones;
	private String emails;
	private int bossId;
	private String bossName;
	private String bossSurname;
	private int accId;
	private String accName;
	private String accSurname;
	
	public RefCompany(int id, String name, String surname, 
			String code, Date registered, String address, String phones, String emails, 
			int bossId, String bossName, String bossSurname,
			int accId, String accName, String accSurname) {
		
		super(id, name, surname);
		
		this.code = code;
		this.registered = registered;
		this.address = address;
		this.phones = phones;
		this.emails = emails;
		this.bossId = bossId;
		this.bossName = bossName;
		this.bossSurname = bossSurname;
		this.accId = accId;
		this.accName = accName;
		this.accSurname = accSurname;
	}

	public String getCode() {
		return code;
	}

	public Date getRegistered() {
		return registered;
	}

	public String getAddress() {
		return address;
	}

	public String getPhones() {
		return phones;
	}

	public String getEmails() {
		return emails;
	}
	
	public int getBossId() {
		return bossId;
	}

	public String getBossName() {
		return bossName + " " + bossSurname;
	}

	public int getAccId() {
		return accId;
	}
	
	public String getAccName() {
		return accName + " " + accSurname;
	}
	
	@Override
    public String toString() {
		return super.surname;
    }	
	
}