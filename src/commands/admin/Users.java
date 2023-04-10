package commands.admin;

import commands.level.AdminCommand;
import commands.login.AccountService;
import contracts.Command;

public class Users extends AdminCommand {
    private AccountService accountService;

    public Users(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(String[] args) {
        //add logic for command arguments validation
        String subcommandAsText = args[0];
        Command subcommand;

        switch (subcommandAsText.toUpperCase()){
            case "ADD" -> subcommand = new UsersAdd(accountService);
            case "REMOVE" -> subcommand = new UsersRemove(accountService);
            default -> throw new IllegalArgumentException("Invalid subcommand \"" + subcommandAsText + "\"!\nList of available subcommands: \"add\", \"remove\"");
        }

        return subcommand.execute(args);
    }
}
