package commands.guest;

import commands.login.LoginVerifier;
import contracts.Account;
import contracts.Reader;
import contracts.Writer;
import enums.PermissionLevel;
import singleton.LoggedInUser;
import commands.level.GuestCommand;


public class Login extends GuestCommand {
    public Login(LoggedInUser loggedInUser) {
        super(loggedInUser);
    }

    @Override
    public void execute(String[] args, Reader reader, Writer writer) {
        if (getLoggedInUser().getPermissionLevel() == PermissionLevel.ADMIN || getLoggedInUser().getPermissionLevel() == PermissionLevel.CLIENT) {
            writer.writeLine("You are already logged in!");
            return;
        }

        writer.writeLine("Enter username:");
        String username = reader.readLine();
        writer.writeLine("Enter password:");
        String password = reader.readLine();

        LoginVerifier loginVerifier = new LoginVerifier();
        if (loginVerifier.doesAccountExist(username, password)){
            Account account = loginVerifier.getMatchingAccount(username, password);
            getLoggedInUser().setUser(account);
        } else {
            writer.writeLine("Invalid credentials!");
        }
    }
}
