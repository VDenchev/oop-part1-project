package models.roles;

import commands.contracts.AdminCommand;
import commands.contracts.ClientCommand;
import commands.contracts.GuestCommand;
import commands.contracts.Command;
import commands.implementations.authentication.Login;
import commands.implementations.file.Open;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;

public class Guest implements User {
    public static final String FILE_NOT_OPENED_MESSAGE = "You must open a file first! Use command: open <filename>";
    public static final String CLIENT_ACCESS_DENIED_MESSAGE = "You need to be logged in to use this command!";
    public static final String ADMIN_ACCESS_DENIED_MESSAGE = "Only admins have access to this command!";

    public String defaultVisit(Command command, List<String> args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened() ? command.execute(args) : FILE_NOT_OPENED_MESSAGE;
    }

    @Override
    public String visit(GuestCommand guestCommand, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(guestCommand,args, libraryFile);
    }

    @Override
    public String visit(ClientCommand clientCommand, List<String> args, LibraryFile libraryFile) {
        return CLIENT_ACCESS_DENIED_MESSAGE;
    }

    @Override
    public String visit(AdminCommand adminCommand, List<String> args, LibraryFile libraryFile) {
        return ADMIN_ACCESS_DENIED_MESSAGE;
    }

    @Override
    public String visit(Open open, List<String> args, LibraryFile libraryFile) {
        return open.execute(args);
    }

    @Override
    public String visit(Login login, List<String> args, LibraryFile libraryFile) {
        return defaultVisit(login, args, libraryFile);
    }
}
