package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestStudent {

    private Student student;
    private static final String TEST_NAME = "John Doe";
    private static final String TEST_EMAIL = "john.doe@email.com";
    private static final String TEST_ADDRESS = "123 Test Street";

    @BeforeEach
    void setUp(){
        student = new Student(TEST_NAME, TEST_EMAIL, TEST_ADDRESS);
    }

    @Test
    void constructor_ShouldSetAllFields() {
        assertNotNull(student);
        assertTrue(student.getId() > 0);
        assertEquals(TEST_NAME, student.getName());
        assertEquals(TEST_EMAIL, student.getEmail());
        assertEquals(TEST_ADDRESS, student.getAddress());
    }

    @Test
    void constructor_ShouldIncrementId() {
        Student student1 = new Student("Test1", "test1@email.com", "Address1");
        Student student2 = new Student("Test2", "test2@email.com", "Address2");
        assertEquals(student1.getId() + 1, student2.getId());
    }

    @Test
    void setName_ShouldUpdateName() {
        String newName = "Jane Doe";
        student.setName(newName);
        assertEquals(newName, student.getName());
    }

    @Test
    void setEmail_ShouldUpdateEmail() {
        String newEmail = "jane.doe@email.com";
        student.setEmail(newEmail);
        assertEquals(newEmail, student.getEmail());
    }

    @Test
    void setAddress_ShouldUpdateAddress() {
        String newAddress = "456 New Street";
        student.setAddress(newAddress);
        assertEquals(newAddress, student.getAddress());
    }


    @Test
    void toString_ShouldReturnCorrectFormat() {
        String expected = "Student{id=" + student.getId() + ", name='" + TEST_NAME + "'}";
        assertEquals(expected, student.toString());
    }


}
