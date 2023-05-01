package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.parser.contracts.IMarshaller;
import models.parser.exceptions.ParserException;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.io.File;
import java.util.List;

public class SaveAs implements GuestCommand {
    public static final String ERROR_SAVING_TO_FILE = "An error has occurred while saving to %s!";
    public static final String SUCCESS_MESSAGE = "Successfully saved %s!";
    public static final String INCORRECT_USAGE = "Incorrect usage! Try typing: saveas <filename>";
    public static final int CORRECT_ARGS_COUNT = 2;

    private Library library;
    private IMarshaller marshaller;

    public SaveAs(Library library, IMarshaller marshaller) {
        this.library = library;
        this.marshaller = marshaller;
    }

    @Override
    public String execute(List<String> args) {
        String fileName = args.get(1);

        try {
            LibraryFile libraryFile = new LibraryFile(fileName, "xml");
            marshaller.marshal(library, new File(libraryFile.getFile()));
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (ParserException e) {
            return ERROR_SAVING_TO_FILE;
            //logger
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
