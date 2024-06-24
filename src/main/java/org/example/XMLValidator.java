package org.example;

import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLValidator {
    public static void main(String[] args) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.XML_DTD_NS_URI);
            Schema schema = factory.newSchema(new File("Student.xsd"));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File("Student.xml")));
            System.out.println("valid");


        } catch (Exception e){
            System.out.println("ne validiska");
            e.printStackTrace();
        }
    }
}
