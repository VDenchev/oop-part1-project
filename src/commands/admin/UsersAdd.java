package commands.admin;

import commands.level.AdminCommand;
import commands.login.AccountService;

public class UsersAdd extends AdminCommand {
    private AccountService accountService;

    public UsersAdd(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(String[] args){
        //TODO add logic for verifying user input ??maybe throw an exception??
        try {
            accountService.addAccountToFile(args[1], args[2]);
            return "Successfully added a new account";
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
