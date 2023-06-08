package models.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import models.book.Library;
import models.parser.contracts.IParser;
import models.parser.exceptions.ParserException;

import java.io.File;

public class XmlParser implements IParser {
    public void marshal(Library library, File file) throws ParserException {
        try {
            JAXBContext context = JAXBContext.newInstance(library.getClass());
            Marshaller marshaller = context.createMarshaller();

            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(library, file);
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }

    @Override
    public Library unmarshal(Class<Library> libraryClass, File file) throws ParserException {
        try {
            JAXBContext context = JAXBContext.newInstance(libraryClass);
            return (Library) (context.createUnmarshaller().unmarshal(file));
        } catch (JAXBException e) {
            throw new ParserException(e.getMessage(), e);
        }
    }
}
