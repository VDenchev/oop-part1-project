package models.roles;

import commands.contracts.AdminCommand;
import commands.contracts.ClientCommand;
import commands.contracts.GuestCommand;
import commands.contracts.Command;
import commands.implementations.authentication.Login;
import commands.implementations.file.Open;
import models.roles.contracts.Account;
import models.wrappers.LibraryFile;

import java.util.List;

public class Admin extends Account {
    public static final String FILE_NOT_OPENED_MESSAGE = "You must open a file first! Use command: open <filename>";
    public static final String ALREADY_LOGGED_IN_MESSAGE = "You are already logged in!";

    public Admin(String username, String password) {
        super(username, password, PermissionLevel.ADMIN);
    }

    @Override
    public String defaultVisit(Command command, List<String> args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened() ? command.execute(args) : FILE_NOT_OPENED_MESSAGE;
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

    @Override
    public String visit(Login login, List<String> args, LibraryFile libraryFile) {
        return ALREADY_LOGGED_IN_MESSAGE;
    }
}
