package models.wrappers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class LibraryFile {
    private File file;
    private String extension;

    public LibraryFile(String fileName, String extension) throws IOException{
        this.extension = extension;
        setFile(fileName);
    }

    public LibraryFile(String extension) {
        this.extension = extension;
    }

    public boolean fileExtensionMatches(String file) {
        String targetExtension = "";
        int index = file.lastIndexOf(".");
        if (index != -1) {
            targetExtension = file.substring(index + 1);
        }

        return targetExtension.equals(extension);
    }

    public void setFile(String fileName) throws IOException {
        if (!fileExtensionMatches(fileName)) {
            throw new IllegalArgumentException("The system only supports files with \"." + extension + "\" extension!");
        }
        this.file = new File(fileName);

        File parentDir = file.getParentFile();
        if (parentDir != null) {
            parentDir.mkdirs();
        }

        if (!file.exists()) {
            file.createNewFile();
            PrintWriter pw = new PrintWriter(file);
            pw.write("<library/>");
            pw.close();
        }
    }

    public void clearFile() {
        file = null;
    }

    public boolean isFileOpened() {
        return file != null;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return file.getPath();
    }

    public String getExtension() {
        return extension;
    }
}
