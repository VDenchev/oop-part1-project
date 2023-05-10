package commands.implementations.books;

import commands.contracts.AdminCommand;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;

public class BooksRemove implements AdminCommand {
    public static final String SUCCESS_MESSAGE = "Successfully removed book with id %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: books remove <id>";
    public static final int CORRECT_ARGS_COUNT = 3;

    private Library library;

    public BooksRemove(Library library) {
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        int bookId = Integer.parseInt(args.get(2));
        library.removeBookById(bookId);

        return String.format(SUCCESS_MESSAGE, bookId);
    }

    @Override
    public String accept(User user, List<String> args, LibraryFile libraryFile) {
        if(!isValidArgsCount(args.size())) {
            return INCORRECT_USAGE;
        }
        return user.visit(this, args, libraryFile);
    }

    @Override
    public boolean isValidArgsCount(int argsCount) {
        return argsCount >= CORRECT_ARGS_COUNT;
    }
}
