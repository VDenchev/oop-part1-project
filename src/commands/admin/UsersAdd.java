package commands.admin;

import commands.base.AdminCommand;
import commands.services.AccountService;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

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
        try {
            accountService.addAccountToFile(args.get(2), args.get(3));
            return SUCCESS_MESSAGE;
        } catch (Exception e) {
            return e.getMessage();
        }
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
