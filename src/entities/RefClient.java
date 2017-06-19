package entities;

import superclasses.EntityRef;

public class RefClient extends EntityRef {
    private int points;
    private String postalCode;

	public RefClient(int id, String name, String surname, int points, String postalCode) {
    	super(id, name, surname);
    	
        this.postalCode = postalCode;
        this.points = points;
    }
	
	public int getPoints() {
        return points;
    }
	
	public String getPostalCode() {
        return postalCode;
    }
	
}