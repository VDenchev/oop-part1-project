package commands.implementations.authentication;

import commands.services.AccountService;
import models.roles.contracts.Account;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;
import models.wrappers.CurrentUser;
import commands.contracts.GuestCommand;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;


public class Login implements GuestCommand {
    public static final String SUCCESS_MESSAGE = "Welcome %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: login";
    public static final int CORRECT_ARGS_COUNT = 1;

    private Scanner scanner;
    private CurrentUser currentUser;
    private AccountService accountService;

    public Login(Scanner scanner, CurrentUser currentUser, AccountService accountService) {
        this.scanner = scanner;
        this.currentUser = currentUser;
        this.accountService = accountService;
    }

    @Override
    public String execute(List<String> args) {
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();

        try{
            Account account = accountService.getAccountFromFile(username, password);
            currentUser.login(account);

            return String.format(SUCCESS_MESSAGE, username);
        } catch (IOException e) {
            return e.getMessage();
        }
    }
    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        if(!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
