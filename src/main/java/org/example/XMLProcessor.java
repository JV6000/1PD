package org.example;

import javax.xml.bind.*;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.IOException;
import javax.xml.transform.stream.StreamSource;

public class XMLProcessor {
    private JAXBContext context;
    private Schema schema;

    public XMLProcessor(Class<?>... classesToBeBound) throws JAXBException, SAXException, IOException {

        try {
            context = JAXBContext.newInstance(classesToBeBound);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            schema = schemaFactory.newSchema(XMLProcessor.class.getResource("Student.xsd"));
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void processXML(String xml) throws JAXBException, SAXException {
        try {

            validateXML(xml);

            Student student = transformToPOJO(xml);

            System.out.println("Received Student Details:");
            System.out.println("ID: " + student.getStudentId());
            System.out.println("Name: " + student.getStudentName());
            System.out.println("Age: " + student.getAge());

            String transformedXML = transformToXML(student);
            System.out.println("\nTransformed XML:");
            System.out.println(transformedXML);
        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private void validateXML(String xml) throws SAXException {
        try {
            Validator validator = schema.newValidator();
            try {
                validator.validate(new StreamSource(new StringReader(xml)));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (SAXException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private Student transformToPOJO(String xml) throws JAXBException {
        try {
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Student) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private String transformToXML(Student student) throws JAXBException {
        try {
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter writer = new StringWriter();
            marshaller.marshal(student, writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
