package commands.client;

import commands.base.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.util.ArrayList;
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
        try {
            Collection<Book> booksFound = switch (option.toUpperCase()) {
                case "TITLE" -> library.getBooksByTitle(optionString);
                case "AUTHOR" -> library.getBooksByAuthorFullName(optionString);
                case "TAG" -> library.getBooksByKeyword(optionString);
                default -> throw new IllegalArgumentException(INCORRECT_USAGE);
            };

            StringBuilder sb = new StringBuilder(String.format("%d BOOKS FOUND%n", booksFound.size()));

            for (Book book: booksFound) {
                sb.append(book).append("\n");
            }

            return sb.toString();

        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
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

    public List<String> parseArguments(List<String> args) {
        List<String> parsedArgs = args.subList(0, 3);
        parsedArgs.add(String.join(" ", args.subList(3, args.size())));

        return parsedArgs;
    }
}
