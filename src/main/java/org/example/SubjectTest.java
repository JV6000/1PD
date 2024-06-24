package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SubjectTest {
    @Test
    void testSubjectConstructor() {
        Subject subject = new Subject();
        assertNotNull(subject);
    }

}
