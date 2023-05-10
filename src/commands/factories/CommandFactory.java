package commands.factories;

import commands.contracts.Command;
import commands.implementations.WrongCommand;
import commands.implementations.authentication.*;
import commands.implementations.books.Books;
import commands.implementations.file.*;
import commands.implementations.users.Users;
import commands.services.AccountService;
import models.book.Library;
import models.parser.contracts.IParser;
import models.wrappers.CurrentUser;
import models.wrappers.LibraryFile;

import java.util.Scanner;

public class CommandFactory {

    public static final String INCORRECT_COMMAND = "Incorrect command %s! Type \"help\" for a list with available commands";

    public Command createCommand(String commandAsText, LibraryFile libraryFile, Library library, IParser parser, Scanner scanner, CurrentUser currentUser, AccountService accountService) {
        return switch (commandAsText.toUpperCase()){
            case "OPEN" -> new Open(libraryFile, library, parser);
            case "SAVE" -> new Save(libraryFile, library, parser);
            case "SAVEAS" -> new SaveAs(library, parser);
            case "CLOSE" -> new Close(libraryFile, library);
            case "LOGIN" -> new Login(scanner, currentUser, accountService);
            case "LOGOUT" -> new Logout(currentUser);
            case "BOOKS" -> new Books(scanner, currentUser, libraryFile, library);
            case "USERS" -> new Users(accountService, currentUser, libraryFile);
            default -> new WrongCommand(String.format(INCORRECT_COMMAND, commandAsText));
        };
    }
}
