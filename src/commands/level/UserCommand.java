package commands.level;

import singleton.LoggedInUser;
import enums.PermissionLevel;
import contracts.Command;

public abstract class UserCommand extends Command {
    public UserCommand(LoggedInUser loggedInUser) {
        super(loggedInUser,
                new PermissionLevel[]{
                        PermissionLevel.ADMIN,
                        PermissionLevel.CLIENT
        });
    }
}
