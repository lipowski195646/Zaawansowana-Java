package entities;

import java.util.Date;

import superclasses.EntityRef;

public class RefUser extends EntityRef {
	private int accountId;
	private String login;
	private String socialId;
	private String state;
	private float salary;
	private float premia;
	private Date dateIn;
	private Date dateOut;
	private String address;

	public RefUser(int id, int accountId, String login, String name, String surname, String socialId, String state, float salary, float premia, Date dateIn, Date dateOut, String address) {
		super(id, name, surname);
    	
    	this.accountId = accountId;
    	this.login = login;
    	this.socialId = socialId;
    	this.state = state;
    	this.salary = salary;
    	this.premia = premia;
    	this.dateIn = dateIn;
		this.dateOut = dateOut;
		this.address = address;
    }
    
	public String getLogin() {
		return login;
	}

	public int getAccountId() {
		return accountId;
	}

	public String getSocialId() {
		return socialId;
	}
	
	public String getState() {
		return state;
	}

	public float getSalary() {
		return salary;
	}
	
	public float getPremia() {
		return premia;
	}
	
	public Date getDateIn() {
		return dateIn;
	}
	
	public Date getDateOut() {
		return dateOut;
	}
	
	public String getAddress() {
		return address;
	}
	
}