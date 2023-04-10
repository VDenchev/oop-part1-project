package commands.level;

import enums.PermissionLevel;
import contracts.Command;

public abstract class AdminCommand extends Command {
    public AdminCommand() {
        super(new PermissionLevel[]{
                PermissionLevel.ADMIN
        });
    }
}
