package commands.contracts;

import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;


public interface Command {
    String execute(List<String> args);

    String accept(User user, List<String> args, LibraryFile libraryFile);

    boolean isValidArgsCount(int argsCount);
}
