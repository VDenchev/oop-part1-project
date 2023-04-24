package commands.admin;

import commands.base.AdminCommand;
import commands.services.AccountService;
import commands.contracts.Command;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

public class Users implements AdminCommand {
    private AccountService accountService;

    public Users(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public String execute(String[] args) {
        //add logic for command arguments validation
        String subcommandAsText = args[1];
        Command subcommand;

        switch (subcommandAsText.toUpperCase()){
            case "ADD" -> subcommand = new UsersAdd(accountService);
            case "REMOVE" -> subcommand = new UsersRemove(accountService);
            default -> throw new IllegalArgumentException("Invalid subcommand \"" + subcommandAsText + "\"!\nList of available subcommands: \"add\", \"remove\"");
        }

        return subcommand.execute(args);
    }

    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
