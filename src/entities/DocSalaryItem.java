package entities;

import superclasses.EntityItem;

public class DocSalaryItem extends EntityItem {
	private int userId;
	private String userName;
	private String userSurname;
	private float salaryRef;
	private int workDays;
	private float salaryCalc;
	private float premiaRef;
	private float premiaCalc;
	
	public DocSalaryItem(int id, int userId, String userName, String userSurname, float salaryRef, int workDays, float salaryCalc, float premiaRef, float premiaCalc, float summa) {
		super(id, summa);
		
		this.userId = userId;
		this.userName = userName;
		this.userSurname = userSurname;
		this.salaryRef = salaryRef;
		this.workDays = workDays;
		this.salaryCalc = salaryCalc;
		this.premiaRef = premiaRef;
		this.premiaCalc = premiaCalc;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName + " " + userSurname;
	}

	public float getSalaryRef() {
		return salaryRef;
	}

	public int getWorkDays() {
		return workDays;
	}

	public float getSalaryCalc() {
		return salaryCalc;
	}

	public float getPremiaRef() {
		return premiaRef;
	}

	public float getPremiaCalc() {
		return premiaCalc;
	}

	@Override
    public String toString() {
        return getUserName() + ": " + workDays + " day(s) / " + super.getSumma();
    }

}