package commands.implementations.books;

import commands.contracts.Command;
import commands.contracts.GuestCommand;
import commands.implementations.WrongCommand;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;
import models.wrappers.CurrentUser;

import java.util.List;
import java.util.Scanner;

public class Books implements GuestCommand {
    public static final String INVALID_SUBCOMMAND = "Invalid subcommand \"%s\"! List of available subcommands: all, info, find, sort, add, remove";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books <subcommand>, where <subcommand> is one of the following: add, remove";
    public static final int CORRECT_ARGS_COUNT = 2;

    private Scanner scanner;
    private CurrentUser currentUser;
    private LibraryFile libraryFile;
    private Library library;


    public Books(Scanner scanner, CurrentUser currentUser, LibraryFile libraryFile, Library library) {
        this.scanner = scanner;
        this.currentUser = currentUser;
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
            case "SORT" -> new BooksSort(library);
            case "ADD" -> new BooksAdd(library, scanner);
            case "REMOVE" -> new BooksRemove(library);
            default -> new WrongCommand(String.format(INVALID_SUBCOMMAND, subcommandAsText));
        };

        return command.accept(currentUser.getUser(), args, libraryFile);
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
