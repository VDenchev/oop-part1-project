package models.roles.contracts;

import commands.contracts.AdminCommand;
import commands.contracts.ClientCommand;
import commands.contracts.Command;
import commands.contracts.GuestCommand;
import commands.implementations.authentication.Login;
import commands.implementations.file.Open;
import models.wrappers.LibraryFile;

import java.util.List;

public interface User {
    String defaultVisit(Command command, List<String> args, LibraryFile libraryFile);

    String visit(GuestCommand guestCommand, List<String> args, LibraryFile libraryFile);

    String visit(ClientCommand clientCommand, List<String> args, LibraryFile libraryFile);

    String visit(AdminCommand adminCommand, List<String> args, LibraryFile libraryFile);

    String visit(Open open, List<String> args, LibraryFile libraryFile);

    String visit(Login login, List<String> args, LibraryFile libraryFile);
}
