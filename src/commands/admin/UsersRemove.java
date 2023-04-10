package commands.admin;

import commands.level.AdminCommand;
import commands.login.AccountService;

public class UsersRemove extends AdminCommand {

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
}
