package my.vaadin.app;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class UserManage implements UserDAO, Serializable {

    List<User> users;

    public UserManage() {
        users = new LinkedList<User>();
        users.add(new User("test22", "test22"));
        users.add(new User("test33", "test33"));
    }

    @Override
	public boolean getUserBy(String username, String password) {
        for (User user : users) {
            if (user.getName().equals(username)
                    && user.getPass().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public User getUserBy(String username) {
        for (User user : users) {
            if (user.getName().equals(username)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean saveUser(User user) {
        try {
            if (users.contains(user)) {
                return true;
            } else {
                users.add(user);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

}