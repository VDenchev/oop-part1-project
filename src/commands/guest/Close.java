package commands.guest;

import commands.base.GuestCommand;
import models.book.Library;
import models.roles.Visitor;
import models.wrappers.LibraryFile;

public class Close implements GuestCommand {
    private LibraryFile libraryFile;
    private Library library;

    public Close(LibraryFile libraryFile, Library library) {
        this.libraryFile = libraryFile;
        this.library = library;
    }

    @Override
    public String execute(String[] args) {
        String closedFile = libraryFile.getFile();
        libraryFile.clearFile();
        library.clear();
        return "Successfully closed " + closedFile + "!";
    }
    @Override
    public String accept(Visitor visitor, String[] args, LibraryFile libraryFile) {
        return visitor.visit(this, args, libraryFile);
    }
}
