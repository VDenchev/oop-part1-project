package models.parser.contracts;

import models.book.Library;
import models.parser.exceptions.ParserException;

import java.io.File;

public interface IMarshaller {
    void marshal(Library library, File file) throws ParserException;
}
