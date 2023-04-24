package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.parser.contracts.IMarshaller;
import models.parser.exceptions.ParserException;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.io.File;

public class Save implements GuestCommand {
    private LibraryFile libraryFile;
    private Library library;
    private IMarshaller marshaller;

    public Save(LibraryFile libraryFile, Library library, IMarshaller marshaller) {
        this.libraryFile = libraryFile;
        this.library = library;
        this.marshaller = marshaller;
    }

    @Override
    public String execute(String[] args) {
        File file = new File(libraryFile.getFile());
        String fileName = libraryFile.getFile();
        try {
            marshaller.marshal(library, file);
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
