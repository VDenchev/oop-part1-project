package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.contracts.Command;
import commands.guest.Open;
import models.wrappers.LibraryFile;

import java.util.List;

public interface Visitor {
    String defaultVisit(Command command, List<String> args, LibraryFile libraryFile);
    String visit(GuestCommand guestCommand, List<String> args, LibraryFile libraryFile);
    String visit(ClientCommand clientCommand, List<String> args, LibraryFile libraryFile);
    String visit(AdminCommand adminCommand, List<String> args, LibraryFile libraryFile);
    String visit(Open open, List<String> args, LibraryFile libraryFile);

}
