package commands.contracts;

import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.util.List;


public interface Command {
    String execute(List<String> args);
    String accept(Visitor visitor, List<String> args, LibraryFile libraryFile);
    boolean isValidArgsCount(int argsCount);
}
