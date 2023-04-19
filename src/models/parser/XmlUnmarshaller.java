package models.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import models.book.Library;

import java.io.File;

public class XmlUnmarshaller {
    public static Library unmarshal(Class<Library> libraryClass, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(libraryClass);
        return libraryClass.cast(context.createUnmarshaller().unmarshal(file));
    }
}
