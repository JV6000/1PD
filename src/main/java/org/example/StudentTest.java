package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {
    @Test
    void testStudentConstructor() {
        Student student = new Student();
        assertNotNull(student);
    }

}
