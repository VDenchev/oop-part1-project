package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.contracts.Command;
import commands.guest.Open;
import contracts.User;
import enums.PermissionLevel;
import models.wrappers.LibraryFile;

import java.util.List;

public class Guest implements User {
    private PermissionLevel permissionLevel;

    public Guest() {
        permissionLevel = PermissionLevel.GUEST;
    }

    @Override
    public PermissionLevel getPermissionLevel() {
        return permissionLevel;
    }

    public String defaultVisit(Command command, List<String> args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened() ? command.execute(args) : "You must open a file first! Use command: open <filename>";
    }

    @Override
    public String visit(GuestCommand guestCommand, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(guestCommand,args, libraryFile);
    }

    @Override
    public String visit(ClientCommand clientCommand, List<String> args, LibraryFile libraryFile) {
        return "You need to be logged in to use this command!";
    }

    @Override
    public String visit(AdminCommand adminCommand, List<String> args, LibraryFile libraryFile) {
        return "Only admins have access to this command!";
    }

    @Override
    public String visit(Open open, List<String> args, LibraryFile libraryFile) {
        return open.execute(args);
    }
}
