package commands.implementations.books;

import commands.contracts.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksAll implements ClientCommand {
    public static final int CORRECT_ARGS_COUNT = 2;
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books all";

    private Library library;

    public BooksAll(Library library) {
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        List<String> tableHeaders = new ArrayList<>(Arrays.asList("Title", "Author", "Genre", "ISBN"));
        List<Integer> columnWidths = calculateColumnWidths(tableHeaders, library, 2);

        String rowFormat = columnWidths.stream()
                .reduce(new StringBuilder(),
                        (sb, el) -> sb.append("| %-").append(el - 1).append("s"),
                        StringBuilder::append)
                .append("|%n").toString();

        String border = columnWidths.stream()
                .reduce(new StringBuilder(),
                        (sb, el) -> sb.append("+").append(fillString(el, '-')),
                        StringBuilder::append)
                .append("+\n")
                .toString();

        String headers =  String.format(rowFormat, tableHeaders.toArray());
        StringBuilder sb = new StringBuilder(border).append(headers).append(border);
        library.getBooks()
                .forEach(book -> sb.append(String.format(rowFormat, book.getTitle(), book.getAuthor(), book.getGenre(), book.getIsbn())));
        sb.append(border);

        return sb.toString();
    }

    private List<Integer> calculateColumnWidths(List<String> headers, Library library, int padding) {
        List<Integer> columnWidths = new ArrayList<>();
        for (String header : headers) {
            int maxWidth = header.length();
            for (Book book : library.getBooks()) {
                int width = switch (header) {
                    case "Title" -> book.getTitle().length();
                    case "Author" -> book.getAuthor().getFullName().length();
                    case "Genre" -> book.getGenre().length();
                    case "ISBN" -> book.getIsbn().length();
                    default -> 0;
                };
                maxWidth = Math.max(maxWidth, width);
            }
            columnWidths.add(maxWidth + padding);
        }
        return columnWidths;
    }

    private String fillString(int length, char character) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(character);
        }
        return sb.toString();
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
