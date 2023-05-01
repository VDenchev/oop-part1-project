package models.wrappers;

import contracts.User;
import enums.PermissionLevel;

public class LoggedInUser {
    private static LoggedInUser instance;
    private User user;
    private LoggedInUser() {
    }

    public static LoggedInUser getInstance() {
        if (instance == null){
            instance = new LoggedInUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public PermissionLevel getPermissionLevel() {
        return user.getPermissionLevel();
    }

    public void setUser(User user) {
        this.user = user;
    }
}
