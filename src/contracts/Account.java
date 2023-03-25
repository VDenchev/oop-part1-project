package contracts;

import enums.PermissionLevel;

public abstract class Account implements User{
    private String username;
    private String password;
    private PermissionLevel permissionLevel;

    public Account(String username, String password, PermissionLevel permissionLevel) {
        this.username = username;
        this.password = password;
        this.permissionLevel = permissionLevel;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    @Override
    public String toString() {
        return username + ":" + password + ":" + permissionLevel;
    }
}
