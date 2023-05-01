package commands.client;

import commands.base.ClientCommand;
import commands.contracts.Command;
import commands.guest.WrongCommand;
import models.book.Library;
import models.roles.Visitor;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;

import java.util.List;

public class Books implements ClientCommand {
    public static final String INVALID_SUBCOMMAND = "Invalid subcommand \"%s\"! List of available subcommands: all, info,  find, sort, add, remove";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books <subcommand>, where <subcommand> is one of the following: add, remove";
    public static final int CORRECT_ARGS_COUNT = 2;

    private LoggedInUser loggedInUser;
    private LibraryFile libraryFile;
    private Library library;

    public Books(LoggedInUser loggedInUser, LibraryFile libraryFile, Library library) {
        this.loggedInUser = loggedInUser;
        this.libraryFile = libraryFile;
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        String subcommandAsText = args.get(1);

        Command command = switch (subcommandAsText.toUpperCase()) {
            case "ALL" -> new BooksAll(library);
            case "INFO" -> new BooksInfo(library);
            case "FIND" -> new BooksFind(library);
            case "REMOVE" -> new BooksRemove(library);
            default -> new WrongCommand( String.format(INVALID_SUBCOMMAND, subcommandAsText));
        };

        return command.accept(loggedInUser.getUser(), args, libraryFile);
    }

    @Override
    public String accept(Visitor visitor, List<String> args, LibraryFile libraryFile) {
        if (!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return visitor.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
