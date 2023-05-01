package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.parser.contracts.IUnmarshaller;
import models.parser.exceptions.ParserException;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Open implements GuestCommand {
    public static final String ERROR_OPENING_FILE = "Unable to open %s!";
    public static final String ERROR_READING_DATA = "Unable to read data from %s!";
    public static final String SUCCESS_MESSAGE = "Successfully opened %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing open <filename>";
    public static final int CORRECT_ARGS_COUNT = 2;

    private LibraryFile libraryFile;
    private Library library;
    private IUnmarshaller unmarshaller;

    public Open(LibraryFile libraryFile, Library library, IUnmarshaller unmarshaller) {
        this.libraryFile = libraryFile;
        this.library = library;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public String execute(List<String> args) {
        String fileName = args.get(1);

        try {
            libraryFile.setFile(fileName);
            File file = new File(libraryFile.getFile());

            if (!file.exists()) {
                file.createNewFile();
            } else {
                Library unmarshalledLibrary = unmarshaller.unmarshal(Library.class, file);
                library.setBooks(unmarshalledLibrary.getBooks());
            }
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (IOException e) {
            return String.format(ERROR_OPENING_FILE, fileName);
            //TODO implement a logger
        } catch (ParserException e) {
            return String.format(ERROR_READING_DATA, fileName);
        }

        return String.format(SUCCESS_MESSAGE, fileName);
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
