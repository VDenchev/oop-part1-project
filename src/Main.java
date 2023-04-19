import commands.CommandAuthorizer;
import commands.ConsoleReader;
import commands.ConsoleWriter;
import commands.login.AccountDAOImpl;
import commands.login.AccountService;
import contracts.Command;
import contracts.Reader;
import contracts.Writer;
import factories.CommandFactory;
import models.book.Library;
import models.roles.Guest;
import models.singleton.LibraryFile;
import models.singleton.LoggedInUser;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Library library = new Library();

        LoggedInUser user = LoggedInUser.getInstance();
        user.setUser(new Guest());
        LibraryFile libraryFile = new LibraryFile("books.xml", "xml");
        Reader reader = new ConsoleReader(new Scanner(System.in));
        Writer writer = new ConsoleWriter();
        AccountService accountService = new AccountService(new AccountDAOImpl(AccountService.FILE_PATH, AccountService.SEPARATOR));

        CommandFactory commandFactory = new CommandFactory();
        String userInput = reader.readLine();
        String[] arguments = userInput.split(" ");

        try {
            String commandAsText = arguments[0];
            Command command = commandFactory.createCommand(libraryFile, library, commandAsText, reader, writer, user, accountService);
            String result;
            if (CommandAuthorizer.isUserAuthorized(user, command)){
                result = command.execute(arguments);
            } else {
                result = "You don't have access to this command!";
            }

            writer.writeLine(result);
        }catch (IllegalArgumentException e) {
            writer.writeLine(e.getMessage());
        }
        writer.writeLine(user.getPermissionLevel().toString());
    }

}
