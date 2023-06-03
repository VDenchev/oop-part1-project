import commands.CommandLoop;
import commands.dao.AccountDAOImpl;
import commands.services.AccountService;
import commands.factories.CommandFactory;
import models.book.Library;
import models.parser.XmlParser;
import models.parser.contracts.IParser;
import models.wrappers.LibraryFile;
import models.wrappers.CurrentUser;

import java.io.File;
import java.util.Scanner;

public class Main {
    public static final String FILE_PATH = "files" + File.separator + "accounts.txt";
    public static final String SEPARATOR = ":";

    public static void main(String[] args) {
        Library library = new Library();
        CurrentUser user = CurrentUser.getInstance();
        LibraryFile libraryFile = new LibraryFile("xml");
        Scanner scanner = new Scanner(System.in);
        IParser parser = new XmlParser();
        AccountService accountService = new AccountService(new AccountDAOImpl(new File(FILE_PATH), SEPARATOR));
        CommandFactory commandFactory = new CommandFactory();

        CommandLoop commandLoop = new CommandLoop(library, user, libraryFile, parser, accountService, commandFactory, scanner);
        commandLoop.start();
    }

}
