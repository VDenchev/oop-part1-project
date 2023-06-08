package commands.implementations.authentication;

import commands.contracts.ClientCommand;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;
import models.wrappers.CurrentUser;

import java.util.List;

public class Logout implements ClientCommand {
    public static final String SUCCESS_MESSAGE = "Successfully logged out!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: logout";
    public static final int CORRECT_ARGS_COUNT = 1;

    private CurrentUser currentUser;

    public Logout(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public String execute(List<String> args) {
        currentUser.logout();
        return SUCCESS_MESSAGE;
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        if (!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
