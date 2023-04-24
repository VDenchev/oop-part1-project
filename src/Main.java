import commands.ConsoleReader;
import commands.ConsoleWriter;
import dao.AccountDAOImpl;
import commands.services.AccountService;
import commands.contracts.Command;
import contracts.Reader;
import contracts.Writer;
import commands.factories.CommandFactory;
import models.book.Library;
import models.parser.XmlParser;
import models.parser.contracts.IParser;
import models.roles.Guest;
import models.wrappers.LibraryFile;
import models.wrappers.LoggedInUser;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception{
        Library library = new Library();

        LoggedInUser user = LoggedInUser.getInstance();
        user.setUser(new Guest());
        LibraryFile libraryFile = new LibraryFile("books.xml", "xml");
        Reader reader = new ConsoleReader(new Scanner(System.in));
        Writer writer = new ConsoleWriter();
        IParser parser = new XmlParser();
        AccountService accountService = new AccountService(new AccountDAOImpl(AccountService.FILE_PATH, AccountService.SEPARATOR));
        CommandFactory commandFactory = new CommandFactory();

        while (true) {
            System.out.print("Enter command:");
            String userInput = reader.readLine();
            String[] arguments = userInput.split(" ");

            try {
                String commandAsText = arguments[0];
                Command command = commandFactory.createCommand(commandAsText, libraryFile, library, parser, reader, writer, user, accountService);
                String result;
                if (commandAsText.equalsIgnoreCase("EXIT")){
                    result = "Exiting the program...";
                    writer.writeLine(result);
                    break;
                }
                result = command.accept(user.getUser(), arguments, libraryFile);
                writer.writeLine(result);
            } catch (IllegalArgumentException e) {
                writer.writeLine(e.getMessage());
            }
            writer.writeLine(user.getPermissionLevel().toString());
        }
    }

}
