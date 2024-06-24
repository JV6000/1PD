package org.example;

import javax.xml.bind.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

public class JAXBTransformer {
    public String transformToXML(Student student) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        StringWriter writer = new StringWriter();
        marshaller.marshal(student, writer);
        return writer.toString();
    }

    public Student transformToPOJO(String xml) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Student) unmarshaller.unmarshal(new StringReader(xml));
    }
}
