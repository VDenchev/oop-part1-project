package models.wrappers;

public class LibraryFile {
    private String fileName;
    private String extension;

    public LibraryFile(String fileName, String extension) {
        this.extension = extension;
        setFileName(fileName);
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

    public void setFileName(String fileName) {
        if (!fileExtensionMatches(fileName)) {
            throw new IllegalArgumentException("The system only supports files with \"." + extension + "\" extension!");
        }
        this.fileName = fileName;
    }

    public void clearFile() {
        fileName = null;
    }

    public boolean isFileOpened() {
        return fileName != null;
    }

    public String getFileName() {
        return fileName;
    }

    public String getExtension() {
        return extension;
    }
}
