package commands.factories;

import commands.admin.Users;
import commands.client.Books;
import commands.guest.*;
import commands.services.AccountService;
import commands.client.Logout;
import commands.contracts.Command;
import contracts.Reader;
import contracts.Writer;
import models.book.Library;
import models.parser.contracts.IParser;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;

public class CommandFactory {

    public Command createCommand(String commandAsText, LibraryFile libraryFile, Library library, IParser parser, Reader reader, Writer writer, LoggedInUser loggedInUser, AccountService accountService) {
        return switch (commandAsText.toUpperCase()){
            case "OPEN" -> new Open(libraryFile, library, parser);
            case "SAVE" -> new Save(libraryFile, library, parser);
            case "SAVEAS" -> new SaveAs(library, parser);
            case "CLOSE" -> new Close(libraryFile, library);
            case "LOGIN" -> new Login(reader, writer, loggedInUser, accountService);
            case "LOGOUT" -> new Logout(loggedInUser);
            case "BOOKS" -> new Books(loggedInUser, libraryFile, library);
            case "USERS" -> new Users(accountService, loggedInUser, libraryFile);
            default -> new WrongCommand(String.format("Incorrect command %s! Type \"help\" for a list with available commands", commandAsText));
        };
    }
}
