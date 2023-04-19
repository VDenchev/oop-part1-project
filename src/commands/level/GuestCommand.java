package commands.level;

import enums.PermissionLevel;
import contracts.Command;

public abstract class GuestCommand extends Command {
    public GuestCommand() {
        super(new PermissionLevel[]{
                PermissionLevel.ADMIN,
                PermissionLevel.CLIENT,
                PermissionLevel.GUEST
        });
    }
}
