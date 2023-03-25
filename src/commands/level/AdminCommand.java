package commands.level;

import singleton.LoggedInUser;
import enums.PermissionLevel;
import contracts.Command;

public abstract class AdminCommand extends Command {
    public AdminCommand(LoggedInUser loggedInUser) {
        super(loggedInUser,
                new PermissionLevel[]{
                        PermissionLevel.ADMIN
        });
    }
}
