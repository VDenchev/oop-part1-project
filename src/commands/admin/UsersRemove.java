package commands.admin;

import commands.base.AdminCommand;
import commands.services.AccountService;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

public class UsersRemove implements AdminCommand {

    private AccountService accountService;

    public UsersRemove(AccountService accountService) {
        this.accountService = accountService;
    }

    public String execute(String[] args){
        try {
            accountService.removeAccountFromFile(args[1]);
            return "Successfully removed account";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
