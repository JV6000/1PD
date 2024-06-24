package org.example;
import org.junit.jupiter.api.Test;
import javax.xml.bind.JAXBException;
import static org.junit.jupiter.api.Assertions.*;

public class XMLTransformerTest {
    @Test
    void testTransformToXML() {
        XMLTransformer transformer = new XMLTransformer();
        Student student = new Student();
        student.setStudentId("55555");
        student.setStudentName("Jonas Jonaitis");
        student.setAge(20);
        String xml = null;
        try {
            xml = transformer.transformToXML(student);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assertNotNull(xml);
    }

    @Test
    void testTransformToPOJO() {
        XMLTransformer transformer = new XMLTransformer();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><student><age>20</age><studentId>123456</studentId><studentName>John Doe</studentName></student>";
        Student student = null;
        try {
            student = transformer.transformToPOJO(xml);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        assertNotNull(student);
    }

}
