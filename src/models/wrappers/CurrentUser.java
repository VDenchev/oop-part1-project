package models.wrappers;

import models.roles.Guest;
import models.roles.contracts.User;

public class CurrentUser {
    private static CurrentUser instance;
    private User user;
    private CurrentUser() {
        this.user = new Guest();
    }

    public static CurrentUser getInstance() {
        if (instance == null) {
            instance = new CurrentUser();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public void login(User user) {
        this.user = user;
    }

    public void logout() {
        this.user = new Guest();
    }
}
