package commands.guest;

import commands.level.GuestCommand;
import jakarta.xml.bind.JAXBException;
import models.book.Library;
import models.parser.XmlUnmarshaller;
import models.singleton.LibraryFile;

import java.io.File;
import java.io.IOException;

public class Open extends GuestCommand {
    private LibraryFile libraryFile;
    private Library library;

    public Open(LibraryFile libraryFile, Library library) {
        this.libraryFile = libraryFile;
        this.library = library;
    }

    @Override
    public String execute(String[] args) {
        libraryFile.setFile(args[1]);
        File file = new File(libraryFile.getFile());
        try {
            if (!file.exists()) {
                file.createNewFile();
            } else {
                Library unmarshalledLibrary = XmlUnmarshaller.unmarshal(Library.class, file);
                library.setBooks(unmarshalledLibrary.getBooks());
            }
        } catch(IOException e) {
            return e.getMessage();
            //TODO implement a logger
        } catch (JAXBException e) {
            return "Unable to read data from \"" + libraryFile.getFile() + "\"!";
        }

        return "Successfully opened \"" + libraryFile.getFile() + "\"!";
    }
}
