package commands.guest;

import commands.level.GuestCommand;
import models.book.Library;
import models.singleton.LibraryFile;

public class Close extends GuestCommand {
    private LibraryFile libraryFile;
    private Library library;

    @Override
    public String execute(String[] args) {
        String closedFile = libraryFile.getFile();
        libraryFile.setFile("");
        library.clear();
        return "Successfully closed file \"" + closedFile + "\"";
    }
}
