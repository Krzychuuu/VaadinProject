package my.vaadin.app;
import java.io.Serializable;

import my.vaadin.app.User;

public class UserInfo implements Serializable {
    private User user;

    public UserInfo() {
        this.user = null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}