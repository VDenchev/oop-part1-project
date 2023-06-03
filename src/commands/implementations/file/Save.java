package commands.implementations.file;

import commands.contracts.GuestCommand;
import models.book.Library;
import models.parser.contracts.IMarshaller;
import models.parser.exceptions.ParserException;
import models.roles.contracts.User;
import models.wrappers.LibraryFile;

import java.io.File;
import java.util.List;

public class Save implements GuestCommand {
    public static final String ERROR_SAVING_TO_FILE = "An error has occurred while saving to %s!";
    public static final String SUCCESS_MESSAGE = "Successfully saved %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: save";
    public static final int CORRECT_ARGS_COUNT = 1;

    private LibraryFile libraryFile;
    private Library library;
    private IMarshaller marshaller;

    public Save(LibraryFile libraryFile, Library library, IMarshaller marshaller) {
        this.libraryFile = libraryFile;
        this.library = library;
        this.marshaller = marshaller;
    }

    @Override
    public String execute(List<String> args) {
        String fileName = libraryFile.getFileName();

        try {
            marshaller.marshal(library, libraryFile.getFile());
        } catch (ParserException e) {
            return String.format(ERROR_SAVING_TO_FILE, fileName);
        }
        return String.format(SUCCESS_MESSAGE, fileName);
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
