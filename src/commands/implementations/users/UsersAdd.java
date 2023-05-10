package commands.implementations.users;

import commands.contracts.AdminCommand;
import commands.services.AccountService;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.io.IOException;
import java.util.List;

public class UsersAdd implements AdminCommand {
    public static final String SUCCESS_MESSAGE = "Successfully added a new account";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: users add <username> <password>";
    public static final int CORRECT_ARGS_COUNT = 4;

    private AccountService accountService;

    public UsersAdd(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(List<String> args){
        String username = args.get(2);
        String password = args.get(3);

        try {
            accountService.addAccountToFile(username, password);
            return SUCCESS_MESSAGE;
        } catch (IOException e) {
            return e.getMessage();
        }
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
