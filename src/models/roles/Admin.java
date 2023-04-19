package models.roles;

import contracts.Account;
import enums.PermissionLevel;

public class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password, PermissionLevel.ADMIN);
    }
}
