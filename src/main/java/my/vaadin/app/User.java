package my.vaadin.app;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class User implements Serializable  {

    @NotEmpty
    @Size(min = 5, max = 12)
    private String name;
    private String pass;
    
    public User(String name, String pass){
    	this.name = name;
    	this.pass = pass;
    }
    
    public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}