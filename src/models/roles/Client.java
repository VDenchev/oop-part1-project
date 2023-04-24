package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.guest.Open;
import contracts.Account;
import enums.PermissionLevel;
import models.wrappers.LibraryFile;

public class Client extends Account {

    public Client(String username, String password) {
        super(username, password, PermissionLevel.CLIENT);
    }

    @Override
    public String visit(GuestCommand guestCommand, String[] args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened()? guestCommand.execute(args) : "You must open a file first! Use command: open <filename>";
    }

    @Override
    public String visit(ClientCommand clientCommand, String[] args, LibraryFile libraryFile) {
        return libraryFile.isFileOpened()? clientCommand.execute(args) : "You must open a file first! Use command: open <filename>";
    }

    @Override
    public String visit(AdminCommand adminCommand, String[] args, LibraryFile libraryFile) {
        return "Only admins have access to this command!";
    }

    @Override
    public String visit(Open open, String[] args, LibraryFile libraryFile) {
        return open.execute(args);
    }
}
