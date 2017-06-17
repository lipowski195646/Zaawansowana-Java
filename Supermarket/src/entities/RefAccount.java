package entities;

import superclasses.Entity;

public class RefAccount extends Entity {
    private String login;
    private String password;
    private String role;
    
    public RefAccount(int id, String login, String password, String role) {
    	super(id);
    	
        this.login = login;
        this.password = password;
        this.role = role;
    }
    
    public String getLogin() {
        return login;
    }
 
    public String getPassword() {
        return password;
    }
 
    public String getRole() {
        return role;
    }
 
    @Override
    public String toString() {
        return login + " (role: " + role + ")";
    }
}