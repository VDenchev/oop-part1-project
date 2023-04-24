package models.roles;

import commands.base.AdminCommand;
import commands.base.ClientCommand;
import commands.base.GuestCommand;
import commands.guest.Open;
import contracts.Account;
import enums.PermissionLevel;
import models.wrappers.LibraryFile;

public class Admin extends Account {
    public Admin(String username, String password) {
        super(username, password, PermissionLevel.ADMIN);
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
        return libraryFile.isFileOpened()? adminCommand.execute(args) : "You must open a file first! Use command: open <filename>";
    }

    @Override
    public String visit(Open open, String[] args, LibraryFile libraryFile) {
        return open.execute(args);
    }
}
