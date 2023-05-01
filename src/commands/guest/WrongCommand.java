package commands.guest;

import commands.base.GuestCommand;
import models.roles.Visitor;
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
    public String accept(Visitor visitor, List<String> args, LibraryFile libraryFile) {
        if (!isValidArgsCount(args.size())) {
            return "Incorrect usage! Try typi— No! Try not. Do. Or do not. There is no try.";
        }
        return visitor.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= 0;
    }
}
