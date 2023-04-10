package contracts;

import singleton.LoggedInUser;
import enums.PermissionLevel;


public abstract class Command {
    private final PermissionLevel[] permissionLevels;

    public Command(PermissionLevel[] permissionLevels) {
        this.permissionLevels = permissionLevels;
    }

    public PermissionLevel[] getPermissionLevels() {
        return permissionLevels;
    }

    public abstract String execute(String[] args);
}
