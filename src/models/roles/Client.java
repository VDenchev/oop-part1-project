package models.roles;

import contracts.Account;
import enums.PermissionLevel;

public class Client extends Account {

    public Client(String username, String password) {
        super(username, password, PermissionLevel.CLIENT);
    }

}
