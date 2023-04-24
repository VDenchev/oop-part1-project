package models.parser.contracts;

import models.book.Library;
import models.parser.exceptions.ParserException;

import java.io.File;

public interface IUnmarshaller {
    Library unmarshal(Class<Library> libraryClass, File file) throws ParserException;
}
