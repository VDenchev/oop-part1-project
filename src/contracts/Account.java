package contracts;

import enums.PermissionLevel;

import java.util.Objects;

public abstract class Account implements User {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account account)) return false;

        if (!Objects.equals(username, account.username)) return false;
        return Objects.equals(password, account.password);
    }

}
