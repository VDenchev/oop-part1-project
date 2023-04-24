package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.guest.Open;
import models.wrappers.LibraryFile;

public interface Visitor {
    String visit(GuestCommand guestCommand, String[] args, LibraryFile libraryFile);
    String visit(ClientCommand clientCommand, String[] args, LibraryFile libraryFile);
    String visit(AdminCommand adminCommand, String[] args, LibraryFile libraryFile);
    String visit(Open open, String[] args, LibraryFile libraryFile);

}
