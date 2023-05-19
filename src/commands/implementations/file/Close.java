package commands.implementations.file;

import commands.contracts.GuestCommand;
import models.book.Library;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.util.List;

public class Close implements GuestCommand {
    public static final String SUCCESS_MESSAGE = "Successfully closed %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: close";
    public static final int CORRECT_ARGS_COUNT = 1;
    private LibraryFile libraryFile;
    private Library library;

    public Close(LibraryFile libraryFile, Library library) {
        this.libraryFile = libraryFile;
        this.library = library;
    }

    @Override
    public String execute(List<String> args) {
        String closedFile = libraryFile.getFileName();
        libraryFile.clearFile();
        library.clear();
        return String.format(SUCCESS_MESSAGE, closedFile);
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
