package commands.implementations.books;

import commands.contracts.AdminCommand;
import models.book.Author;
import models.book.Book;
import models.book.Library;
import models.book.Rating;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BooksAdd implements AdminCommand {
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books add <id>, <isbn>, <title>, <author>, <year published>";
    public static final int CORRECT_ARGS_COUNT = 7;
    public static final String SUCCESS_MESSAGE = "Successfully added book!";

    private Library library;
    private Scanner scanner;

    public BooksAdd(Library library, Scanner scanner) {
        this.library = library;
        this.scanner = scanner;
    }

    @Override
    public String execute(List<String> args) {
        int id, year;
        try {
            id = Integer.parseInt(args.get(2));
        } catch (NumberFormatException e) {
            return "Incorrect value for book id.";
        }
        String isbn = args.get(3);
        String title = args.get(4);
        String[] authorNames = args.get(5).split("\\s+");
        try {
            year = Integer.parseInt(args.get(6));
        } catch (NumberFormatException e) {
            return "Incorrect value for book publishing year";
        }
        Author.Builder authorBuilder = new Author.Builder(authorNames[0]);
        if (authorNames.length > 1) {
            authorBuilder.setLastName(authorNames[1]);
        }

        Book.Builder bookBuilder = new Book.Builder(id, isbn, title, authorBuilder.build(), year);

        System.out.println("Add optional book attributes. (Press enter to skip an attribute)");

        System.out.println("Add genre, or press enter to skip:");
        String genre = scanner.nextLine();
        if (!genre.isBlank()) {
            bookBuilder.setGenre(genre);
        }

        System.out.println("Add description, or press enter to skip:");
        String description = scanner.nextLine();
        if (!description.isBlank()) {
            bookBuilder.setDescription(description);
        }

        System.out.println("Add keywords (separated by a comma - \",\"), or press enter to skip:");
        String[] keywords = scanner.nextLine().split("\\s*,+\\s*");
        if (!keywords[0].isBlank()) {
            bookBuilder.setKeywords(keywords);
        }

        System.out.println("Add rating (0 - 5), or press enter to skip:");
        String ratingString = scanner.nextLine();
        if (!ratingString.isBlank()) {
            bookBuilder.setRating(Rating.getByDescription(ratingString));
        }

        library.addBook(bookBuilder.build());
        return SUCCESS_MESSAGE;
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        List<String> parsedArgs = parseArgs(args);
        if (!isValidArgsCount(parsedArgs.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, parsedArgs, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }

    public List<String> parseArgs(List<String> args) {
        List<String> parsedArgs = args.subList(0, 2);
        String[] bookAttributes = String.join(" ", args.subList(2, args.size())).split("\\s*,\\s*");
        parsedArgs.addAll(Arrays.asList(bookAttributes));
        return parsedArgs;
    }
}
