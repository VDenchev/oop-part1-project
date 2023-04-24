package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.parser.contracts.IMarshaller;
import models.parser.exceptions.ParserException;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.io.File;

public class SaveAs implements GuestCommand {
    private Library library;
    private IMarshaller marshaller;

    public SaveAs(Library library, IMarshaller marshaller) {
        this.library = library;
        this.marshaller = marshaller;
    }

    @Override
    public String execute(String[] args) {
        String fileName = args[1];

        try {
            LibraryFile libraryFile = new LibraryFile(fileName, "xml");
            marshaller.marshal(library, new File(libraryFile.getFile()));
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        } catch (ParserException e) {
            return "An error has occurred while saving to " + fileName + "!";
            //logger
        }
        return "Successfully saved " + fileName + "!";
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
