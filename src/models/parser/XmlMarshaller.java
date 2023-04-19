package models.parser;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import models.book.Library;

import java.io.File;

public class XmlMarshaller {
    public static void marshal(Library library, File file) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(library.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(library, file);
    }
}
