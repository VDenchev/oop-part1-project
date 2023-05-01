package commands.admin;

import commands.base.AdminCommand;
import commands.contracts.Command;
import commands.guest.WrongCommand;
import commands.services.AccountService;
import models.roles.Visitor;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;

import java.util.List;

public class Users implements AdminCommand {
    public static final String INVALID_SUBCOMMAND = "Invalid subcommand \"%s\"! List of available subcommands: add, remove";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: users <subcommand>, where <subcommand> is one of the following: add, remove";
    public static final int CORRECT_ARGS_COUNT = 2;

    private AccountService accountService;
    private LoggedInUser loggedInUser;
    private LibraryFile libraryFile;

    public Users(AccountService accountService, LoggedInUser loggedInUser, LibraryFile libraryFile) {
        this.accountService = accountService;
        this.loggedInUser = loggedInUser;
        this.libraryFile = libraryFile;
    }

    @Override
    public String execute(List<String> args) {
        String subcommandAsText = args.get(1);

        Command command = switch (subcommandAsText.toUpperCase()) {
            case "ADD" -> new UsersAdd(accountService);
            case "REMOVE" -> new UsersRemove(accountService);
            default -> new WrongCommand(String.format(INVALID_SUBCOMMAND, subcommandAsText));
        };

        return command.accept(loggedInUser.getUser(), args, libraryFile);
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
