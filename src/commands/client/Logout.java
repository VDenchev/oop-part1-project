package commands.client;

import commands.base.ClientCommand;
import models.roles.Guest;
import models.roles.Visitor;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;

public class Logout implements ClientCommand {
    private LoggedInUser loggedInUser;

    public Logout(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public String execute(String[] args) {
        loggedInUser.setUser(new Guest());
        return "Successfully logged out!";
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
