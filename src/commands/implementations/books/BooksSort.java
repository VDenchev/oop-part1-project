package commands.implementations.books;

import commands.contracts.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.book.comparators.*;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;
import utils.InsertionSort;

import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BooksSort implements ClientCommand {
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books sort <option> [asc | desc], where <option> is one of the following: title, author, year, rating";
    public static final String SUCCESS_MESSAGE = "Successfully sorted!";
    public static final int MIN_ARGS_COUNT = 3;
    public static final int OPTIONAL_ARGS_COUNT = 4;

    private Library library;

    public BooksSort(Library library) {
        this.library = library;
    }


    @Override
    public String execute(List<String> args) {
        String option = args.get(2);

        Comparator<Book> comparator = switch (option.toUpperCase()) {
            case "TITLE" -> new BookTitleComparator();
            case "AUTHOR" -> new BookAuthorComparator();
            case "YEAR" -> new BookYearComparator();
            case "RATING" -> new BookRatingComparator();
            default -> null;
        };

        if (comparator == null) {
            return INCORRECT_USAGE;
        }

        if (args.size() >= OPTIONAL_ARGS_COUNT && args.get(3).equalsIgnoreCase("DESC")) {
            comparator = new ReverseComparator(comparator);
        }

        Set<Book> sortedBooks = InsertionSort.sort(library.getBooks().stream(), comparator)
                .collect(Collectors.toCollection(LinkedHashSet::new));
        library.setBooks(sortedBooks);

        return SUCCESS_MESSAGE;
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
        return argsCount >= MIN_ARGS_COUNT;
    }
}
