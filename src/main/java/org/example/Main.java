package org.example;

import javax.xml.bind.JAXBException;
import org.xml.sax.SAXException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String receivedXML = "gautas.xml";
        try {
            XMLProcessor processor = new XMLProcessor(Student.class);
            processor.processXML(receivedXML);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
