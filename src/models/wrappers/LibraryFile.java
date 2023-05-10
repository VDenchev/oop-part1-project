package models.wrappers;

public class LibraryFile {
    private String file;
    private String extension;

    public LibraryFile(String file, String extension) {
        this.extension = extension;
        setFile(file);
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

    public void setFile(String file) {
        if (!fileExtensionMatches(file)) {
            throw new IllegalArgumentException("The system only supports files with \"." + extension + "\" extension!");
        }
        this.file = file;
    }

    public void clearFile() {
        file = null;
    }

    public boolean isFileOpened() {
        return file != null;
    }

    public String getFile() {
        return file;
    }

    public String getExtension() {
        return extension;
    }
}
