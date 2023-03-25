package contracts;

import singleton.LoggedInUser;
import enums.PermissionLevel;


public abstract class Command {
    private LoggedInUser loggedInUser;
    private final PermissionLevel[] permissionLevels;

    public Command(LoggedInUser loggedInUser, PermissionLevel[] permissionLevels) {
        this.loggedInUser = loggedInUser;
        this.permissionLevels = permissionLevels;
    }

    public LoggedInUser getLoggedInUser() {
        return loggedInUser;
    }

    // Move to its own class possibly
    public boolean isAuthorized() {
     PermissionLevel userLevel = loggedInUser.getPermissionLevel();

        for (PermissionLevel level : permissionLevels) {
            if (userLevel == level) {
                return true;
            }
        }
        return false;
    }
    public abstract void execute(String[] args, Reader reader, Writer writer);
}
