package commands.implementations.help;

import commands.contracts.GuestCommand;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;

public class Help implements GuestCommand {
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: help";
    public static final int CORRECT_ARGS_COUNT = 1;

    @Override
    public String execute(List<String> args) {
        return """
                                                 The following commands are supported:
              ----------------------------------------------------------------------------------------------------------
                open <file>                             opens <file>
                close                                   closes currently opened file
                save                                    saves the currently open file
                saveas <file>                           saves the currently open file in <file>
                help                                    prints this information
                exit                                    exists the program
                login                                   signs you into your account
              ----------------------------------------------------------------------------------------------------------
              --------------------------------commands which require you to be logged in--------------------------------
              ----------------------------------------------------------------------------------------------------------
                logout                                  signs you out of your account
                books all                               shows a table view of all books in the opened file
                books info <isbn>                       shows more information about book with entered isbn
                books find <option> <option_string>     finds a book by: name, author or tag
                books sort <option> [asc | desc]        sorts all books in opened file by: title, author, year or rating
              ----------------------------------------------------------------------------------------------------------
              --------------------------------commands available only to an administrator-------------------------------
              ----------------------------------------------------------------------------------------------------------
                books add                               adds a new book
                books remove <id>                       removes a book
                users add <username> <password>         adds a new user
                users remove <username>                 removes a user
              ----------------------------------------------------------------------------------------------------------
              """;
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
