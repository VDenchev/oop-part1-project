package commands.implementations.books;

import commands.contracts.ClientCommand;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BooksAll implements ClientCommand {
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books all";
    public static final int CORRECT_ARGS_COUNT = 2;

    private Library library;
    private Scanner scanner;

    public BooksAll(Library library, Scanner scanner) {
        this.library = library;
        this.scanner = scanner;
    }

    @Override
    public String execute(List<String> args) {
        List<String> tableHeaders = new ArrayList<>(Arrays.asList("Title", "Author", "Genre", "ISBN"));
        BooksTableFormatter booksTableFormatter = new BooksTableFormatter(tableHeaders, library.getBooks().stream().toList(), 10);

        int page = 1;
        char input;

        do {
            System.out.println(booksTableFormatter.getPage(page));
            System.out.print("Type: p - previous page, n - next page, enter - exit ");

            String line = scanner.nextLine();
            input = line.isBlank() ? Character.MIN_VALUE : line.charAt(0);

            if (page > 1 && input == 'p') {
                page--;
            }
            if (page < booksTableFormatter.getPagesCount() && input == 'n') {
                page++;
            }
        } while (input != Character.MIN_VALUE);
        return "";
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        if (!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
