package roles;

import contracts.User;
import enums.PermissionLevel;

public class Guest implements User {
    private PermissionLevel permissionLevel;

    public Guest() {
        permissionLevel = PermissionLevel.GUEST;
    }

    @Override
    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }
}
