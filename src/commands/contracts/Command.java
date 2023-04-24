package commands.contracts;

import enums.PermissionLevel;
import models.roles.Visitor;
import models.wrappers.LibraryFile;


public interface Command {
    String execute(String[] args);
    String accept(Visitor visitor, String[] args, LibraryFile libraryFile);
}
