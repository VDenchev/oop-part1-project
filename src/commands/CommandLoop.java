package commands;

import commands.contracts.Command;
import commands.factories.CommandFactory;
import commands.services.AccountService;
import models.book.Library;
import models.parser.contracts.IParser;
import models.wrappers.LibraryFile;
import models.wrappers.CurrentUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CommandLoop {
    private Library library;
    private CurrentUser currentUser;
    private LibraryFile libraryFile;
    private IParser parser;
    private AccountService accountService;
    private CommandFactory commandFactory;
    private Scanner scanner;

    public CommandLoop(Library library, CurrentUser currentUser, LibraryFile libraryFile, IParser parser, AccountService accountService, CommandFactory commandFactory, Scanner scanner) {
        this.library = library;
        this.currentUser = currentUser;
        this.libraryFile = libraryFile;
        this.parser = parser;
        this.accountService = accountService;
        this.commandFactory = commandFactory;
        this.scanner = scanner;
    }

    public void start() {
        char[] arr = new char[49];
        Arrays.fill(arr, '=');
        String border = new String(arr);
        System.out.println(String.format("%s%n%37s%n%s", border, "Library Management System", border));

        while (true) {
            System.out.print("Enter command: ");
            String userInput = scanner.nextLine().trim();
            List<String> arguments = new ArrayList<>(Arrays.asList(userInput.split("\\s+")));

            String commandAsText = arguments.get(0);
            String result;
            if (commandAsText.equalsIgnoreCase("EXIT")) {
                result = "Exiting the program...";
                System.out.println(result);
                break;
            }

            Command command = commandFactory.createCommand(commandAsText, libraryFile, library, parser, scanner, currentUser, accountService);

            try {
                result = command.accept(currentUser.getUser(), arguments, libraryFile);
            } catch (IllegalArgumentException e) {
                result = e.getMessage();
            }
            System.out.println(result);
        }
    }
}
