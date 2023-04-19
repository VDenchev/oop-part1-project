package commands.user;

import commands.level.UserCommand;
import models.roles.Guest;
import models.singleton.LoggedInUser;

public class Logout extends UserCommand {
    private LoggedInUser loggedInUser;

    public Logout(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public String execute(String[] args) {
        loggedInUser.setUser(new Guest());
        return "Successfully logged out!";
    }
}
