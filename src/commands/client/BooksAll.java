package commands.client;

import commands.base.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BooksAll implements ClientCommand {
    public static final int CORRECT_ARGS_COUNT = 2;
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books all";

    private Library library;

    public BooksAll(Library library) {
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        List<String> columnNames = new ArrayList<>(Arrays.asList("Title", "Author", "Genre", "Isbn"));
        List<Integer> columnWidths = new ArrayList<>(columnNames.stream().map(String::length).toList());

        for (Book book : library.getBooks()) {
            if (book.getTitle().length() > columnWidths.get(0)) {
                columnWidths.set(0, book.getTitle().length());
            }
            if (book.getAuthor().getFullName().length() > columnWidths.get(1)){
                columnWidths.set(1, book.getAuthor().getFullName().length());
            }
            if (book.getGenre().length() > columnWidths.get(2)) {
                columnWidths.set(2, book.getGenre().length());
            }
            if (book.getIsbn().length() > columnWidths.get(3)) {
                columnWidths.set(3, book.getIsbn().length());
            }
        }

        columnWidths = columnWidths.stream().map(width -> width + 3).collect(Collectors.toList());

        String rowFormat = columnWidths.stream().reduce(new StringBuilder(), (sb, el) -> sb.append("| %-").append(el - 1).append("s"), StringBuilder::append).append("|%n").toString();

        String border = columnWidths.stream().reduce(new StringBuilder(), (sb, el) -> sb.append("+").append(fillString(el, '-')), StringBuilder::append).append("+\n").toString();

        StringBuilder sb = new StringBuilder(border).append(String.format(rowFormat, columnNames.toArray())).append(border);
        library.getBooks().forEach(book -> sb.append(String.format(rowFormat, book.getTitle(), book.getAuthor(), book.getGenre(), book.getIsbn())));
        sb.append(border);

        return sb.toString();
    }
    protected String fillString(int length, char charToFill) {
        if (length > 0) {
            char[] array = new char[length];
            Arrays.fill(array, charToFill);
            return new String(array);
        }
        return "";
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
}
