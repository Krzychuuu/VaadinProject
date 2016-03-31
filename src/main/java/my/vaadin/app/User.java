package my.vaadin.app;

import org.hibernate.validator.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class User {

    @NotEmpty
    @Size(min = 5, max = 12)
    private String name;
    
    public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}