package commands.admin;

import commands.base.AdminCommand;
import commands.services.AccountService;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

public class UsersAdd implements AdminCommand {
    private AccountService accountService;

    public UsersAdd(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(String[] args){
        //TODO add logic for verifying user input ??maybe throw an exception??
        try {
            accountService.addAccountToFile(args[2], args[3]);
            return "Successfully added a new account";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
