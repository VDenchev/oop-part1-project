package commands.implementations;

import commands.contracts.GuestCommand;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;

public class WrongCommand implements GuestCommand {
    private String message;

    public WrongCommand(String message) {
        this.message = message;
    }

    @Override
    public String execute(List<String> args) {
        return message;
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        if (!isValidArgsCount(args.size())) {
            return "Incorrect usage! Try typi— No! Try not. Do. Or do not. There is no try.";
        }
        return user.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= 0;
    }
}
