package commands.client;

import commands.base.ClientCommand;
import models.roles.Guest;
import models.roles.Visitor;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;

import java.util.List;

public class Logout implements ClientCommand {
    public static final String SUCCESS_MESSAGE = "Successfully logged out!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: logout";
    public static final int CORRECT_ARGS_COUNT = 1;

    private LoggedInUser loggedInUser;

    public Logout(LoggedInUser loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    @Override
    public String execute(List<String> args) {
        loggedInUser.setUser(new Guest());
        return SUCCESS_MESSAGE;
    }

    @Override
    public String accept(Visitor visitor, List<String> args, LibraryFile libraryFile) {
        if(!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return visitor.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
