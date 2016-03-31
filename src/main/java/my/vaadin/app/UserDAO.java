package my.vaadin.app;
import java.util.List;

import my.vaadin.app.User;

public interface UserDAO {

    public boolean getUserBy(String username, String password);
	
	public User getUserBy(String username);

    public boolean saveUser(User user);

    public List<User> getUsers();
}