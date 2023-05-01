package commands.client;

import commands.base.ClientCommand;
import models.book.Book;
import models.book.Library;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.util.List;

public class BooksInfo implements ClientCommand {
    public static final String BOOK_NOT_FOUND = "Book with id %s not found!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books info <isbn>";
    public static final int CORRECT_ARGS_COUNT = 3;

    private Library library;

    public BooksInfo(Library library) {
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        int bookId = Integer.parseInt(args.get(2));
        Book foundBook = library.getBookById(bookId);

        if(foundBook == null) {
            return String.format(BOOK_NOT_FOUND, bookId);
        }
        return foundBook.toString();
    }

    @Override
    public String accept(Visitor visitor, List<String> args, LibraryFile libraryFile) {
        if(!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return visitor.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
