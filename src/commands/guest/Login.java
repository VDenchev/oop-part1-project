package commands.guest;

import commands.services.AccountService;
import contracts.Account;
import contracts.Reader;
import contracts.Writer;
import enums.PermissionLevel;
import models.roles.Visitor;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;
import commands.base.GuestCommand;

import java.util.List;


public class Login implements GuestCommand {
    public static final String SUCCESS_MESSAGE = "Welcome %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: login";
    public static final int CORRECT_ARGS_COUNT = 1;

    private Reader reader;
    private Writer writer;
    private LoggedInUser loggedInUser;
    private AccountService accountService;

    public Login(Reader reader, Writer writer, LoggedInUser loggedInUser, AccountService accountService) {
        this.reader = reader;
        this.writer = writer;
        this.loggedInUser = loggedInUser;
        this.accountService = accountService;
    }

    @Override
    public String execute(List<String> args) {
        if (loggedInUser.getPermissionLevel() == PermissionLevel.ADMIN || loggedInUser.getPermissionLevel() == PermissionLevel.CLIENT) {
            return "You are already logged in!";
        }

        writer.writeLine("Enter username:");
        String username = reader.readLine();
        writer.writeLine("Enter password:");
        String password = reader.readLine();

        try{
            Account account = accountService.getAccountFromFile(username, password);
            loggedInUser.setUser(account);

            return String.format(SUCCESS_MESSAGE, username);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Override
    public String accept(Visitor visitor, List<String> args, LibraryFile libraryFile) {
        if(!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return visitor.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
