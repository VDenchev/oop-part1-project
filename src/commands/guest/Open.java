package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.parser.contracts.IUnmarshaller;
import models.parser.exceptions.ParserException;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

import java.io.File;
import java.io.IOException;

public class Open implements GuestCommand {
    private LibraryFile libraryFile;
    private Library library;
    private IUnmarshaller unmarshaller;

    public Open(LibraryFile libraryFile, Library library, IUnmarshaller unmarshaller) {
        this.libraryFile = libraryFile;
        this.library = library;
        this.unmarshaller = unmarshaller;
    }

    @Override
    public String execute(String[] args) {
        String fileName = args[1];

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
            return "Unable to open " + fileName + "!";
            //TODO implement a logger
        } catch (ParserException e) {
            return "Unable to read data from " + fileName + "!";
        }

        return "Successfully opened " + fileName +"!";
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
