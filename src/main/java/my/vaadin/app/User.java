package my.vaadin.app;

import org.hibernate.validator.constraints.NotEmpty;

import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class User implements Serializable  {

    @NotEmpty
    @Size(min = 5, max = 12)
    private String name;
    
    @NotEmpty
    @Size(min = 5)
    private String pass;
    
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

    BeanItemContainer<User> users =
    		new BeanItemContainer<User>(User.class); 

//    users.addBean(new User("test22", "test22"));
//    users.addBean(new User("test33", "test33"));
//
//    Table table = new Table("Users", users);
//    VerticalLayout layout = new VerticalLayout;
//    layout.addComponent(table); 
}