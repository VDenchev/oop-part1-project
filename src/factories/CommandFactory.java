package factories;

import commands.admin.Users;
import commands.guest.Login;
import commands.guest.Open;
import commands.login.AccountService;
import commands.user.Logout;
import contracts.Command;
import contracts.Reader;
import contracts.Writer;
import models.book.Library;
import models.singleton.LibraryFile;
import models.singleton.LoggedInUser;

public class CommandFactory {

    public Command createCommand(LibraryFile libraryFile, Library library, String commandAsText, Reader reader, Writer writer, LoggedInUser loggedInUser, AccountService accountService) {
        return switch (commandAsText.toUpperCase()){
            case "OPEN" -> new Open(libraryFile, library);
            case "LOGIN" -> new Login(reader, writer, loggedInUser, accountService);
            case "LOGOUT" -> new Logout(loggedInUser);
            case "USERS" -> new Users(accountService);
            default -> throw new IllegalArgumentException("Invalid command!");
        };
    }
}
