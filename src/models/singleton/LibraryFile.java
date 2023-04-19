package models.singleton;

public class LibraryFile {
    private String file;
    private String extension;

    public LibraryFile(String file, String extension) {
        this.extension = extension;
        setFile(file);
    }

   public boolean fileExtensionMatches(String file) {
        String fileExtension = "";
        int index = file.lastIndexOf(".");
        if (index != -1) {
            fileExtension = file.substring(index + 1);
        }

       return extension.equals(fileExtension);
   }

   public void setFile(String file) {
       if (!fileExtensionMatches(file)) {
           throw new IllegalArgumentException("The system only supports files with \"." + extension + "\" extension!");
       }
        this.file = file;
   }

    public String getFile() {
        return file;
    }

    public String getExtension() {
        return extension;
    }
}
