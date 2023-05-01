package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.contracts.Command;
import commands.guest.Open;
import contracts.Account;
import enums.PermissionLevel;
import models.wrappers.LibraryFile;

import java.util.List;

public class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password, PermissionLevel.ADMIN);
    }

    @Override
    public String defaultVisit(Command command, List<String> args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened() ? command.execute(args) : "You must open a file first! Use command: open <filename>";
    }

    @Override
    public String visit(GuestCommand guestCommand, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(guestCommand, args, libraryFile);
    }

    @Override
    public String visit(ClientCommand clientCommand, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(clientCommand, args, libraryFile);
    }

    @Override
    public String visit(AdminCommand adminCommand, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(adminCommand, args, libraryFile);
    }

    @Override
    public String visit(Open open, List<String> args, LibraryFile libraryFile) {
        return open.execute(args);
    }
}
