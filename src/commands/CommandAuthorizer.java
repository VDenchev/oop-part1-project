package commands;

import contracts.Command;
import enums.PermissionLevel;
import singleton.LoggedInUser;

public class CommandAuthorizer {
    public static boolean isUserAuthorized(LoggedInUser loggedInUser, Command command) {
        PermissionLevel userLevel = loggedInUser.getPermissionLevel();
        PermissionLevel[] commandLevels = command.getPermissionLevels();

        for (PermissionLevel level : commandLevels) {
            if (userLevel == level) {
                return true;
            }
        }
        return false;
    }
}
