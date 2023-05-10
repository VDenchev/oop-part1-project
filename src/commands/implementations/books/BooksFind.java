package commands.implementations.books;

import commands.contracts.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.Collection;
import java.util.List;

public class BooksFind implements ClientCommand {
    public static final String INCORRECT_USAGE = "Incorrect usage. Try typing: books find <option> <option_string>, where <option> is one of the following: title, author, tag ";
    public static final int CORRECT_ARGS_COUNT = 4;

    private Library library;

    public BooksFind(Library library) {
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        String option = args.get(2);
        String optionString = args.get(3);

        Collection<Book> booksFound = switch (option.toUpperCase()) {
            case "TITLE" -> library.getBooksByTitle(optionString);
            case "AUTHOR" -> library.getBooksByAuthorFullName(optionString);
            case "TAG" -> library.getBooksByKeyword(optionString);
            default -> null;
        };

        if (booksFound == null) {
            return INCORRECT_USAGE;
        }

        StringBuilder sb = new StringBuilder(String.format("%d BOOKS FOUND%n", booksFound.size()));

        for (Book book: booksFound) {
            sb.append(book).append("\n");
        }

        return sb.toString();
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        List<String> parsedArgs = parseArguments(args);
        if (!isValidArgsCount(parsedArgs.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, parsedArgs, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }

    public List<String> parseArguments(List<String> args) {
        List<String> parsedArgs = args.subList(0, 3);
        parsedArgs.add(String.join(" ", args.subList(3, args.size())));

        return parsedArgs;
    }
}
