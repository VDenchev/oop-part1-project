package factories;

import commands.admin.Users;
import commands.guest.Login;
import commands.login.AccountService;
import commands.user.Logout;
import contracts.Command;
import contracts.Reader;
import contracts.Writer;
import singleton.LoggedInUser;

public class CommandFactory {

    public Command createCommand(String commandAsString, Reader reader, Writer writer, LoggedInUser loggedInUser, AccountService accountService) {
        return switch (commandAsString.toUpperCase()){
            case "LOGIN" -> new Login(reader, writer, loggedInUser, accountService);
            case "LOGOUT" -> new Logout(loggedInUser);
            case "USERS" -> new Users(accountService);
            default -> throw new IllegalArgumentException("Invalid command!");
        };
    }
}
