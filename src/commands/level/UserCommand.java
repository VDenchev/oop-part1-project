package commands.level;

import enums.PermissionLevel;
import contracts.Command;

public abstract class UserCommand extends Command {
    public UserCommand() {
        super(new PermissionLevel[]{
                PermissionLevel.ADMIN,
                PermissionLevel.CLIENT
        });
    }
}
