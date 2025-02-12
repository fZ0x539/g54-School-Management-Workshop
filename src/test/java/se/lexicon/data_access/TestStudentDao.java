package se.lexicon.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Student;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestStudentDao {
    private StudentDaoList studentDao;
    private Student student1;
    private Student student2;
    private Student student3;

    @BeforeEach
    void setUp() {
        // Reset by creating new instance
        studentDao = new StudentDaoList();

        studentDao.findAll().clear();

        // Create test students
        student1 = new Student("John Doe", "john@email.com", "123 Street");
        student2 = new Student("Jane Doe", "jane@email.com", "456 Street");
        student3 = new Student("John Doe", "john.doe@email.com", "789 Street");
    }

    @Test
    void saveStudent_WithValidStudent_ShouldSaveAndReturnStudent() {
        Student savedStudent = studentDao.saveStudent(student1);
        assertNotNull(savedStudent);
        assertEquals(student1, savedStudent);
        assertTrue(studentDao.findAll().contains(student1));
    }

    @Test
    void saveStudent_WithNullStudent_ShouldReturnNull() {
        Student savedStudent = studentDao.saveStudent(null);
        assertNull(savedStudent);
    }

    @Test
    void saveStudent_WithDuplicateStudent_ShouldNotSaveAndReturnNull() {
        studentDao.saveStudent(student1);
        Student duplicateSave = studentDao.saveStudent(student1);
        assertNull(duplicateSave);
        assertEquals(1, studentDao.findAll().size());
    }

    @Test
    void findByEmail_WithExistingEmail_ShouldReturnStudent() {
        studentDao.saveStudent(student1);
        Student found = studentDao.findByEmail("john@email.com");
        assertNotNull(found);
        assertEquals(student1, found);
    }

    @Test
    void findByEmail_WithNonExistingEmail_ShouldReturnNull() {
        Student found = studentDao.findByEmail("nonexistent@email.com");
        assertNull(found);
    }

    @Test
    void findByName_WithExistingName_ShouldReturnMatchingStudents() {
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);
        studentDao.saveStudent(student3);

        List<Student> foundStudents = studentDao.findByName("John Doe");
        assertEquals(2, foundStudents.size());
        assertTrue(foundStudents.contains(student1));
        assertTrue(foundStudents.contains(student3));
    }

    @Test
    void findByName_WithNonExistingName_ShouldReturnEmptyList() {
        List<Student> foundStudents = studentDao.findByName("Non Existent");
        assertTrue(foundStudents.isEmpty());
    }

    @Test
    void findById_WithExistingId_ShouldReturnStudent() {
        studentDao.saveStudent(student1);
        Student found = studentDao.findById(student1.getId());
        assertNotNull(found);
        assertEquals(student1, found);
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnNull() {
        Student found = studentDao.findById(999);
        assertNull(found);
    }

    @Test
    void findAll_WithNoStudents_ShouldReturnEmptyList() {
        List<Student> allStudents = studentDao.findAll();
        System.out.println(allStudents);
        assertTrue(allStudents.isEmpty());
    }

    @Test
    void findAll_WithMultipleStudents_ShouldReturnAllStudents() {
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);

        List<Student> allStudents = studentDao.findAll();
        assertEquals(2, allStudents.size());
        assertTrue(allStudents.contains(student1));
        assertTrue(allStudents.contains(student2));
    }

    @Test
    void deleteStudent_WithExistingStudent_ShouldReturnTrueAndRemoveStudent() {
        studentDao.saveStudent(student1);
        boolean result = studentDao.deleteStudent(student1);
        assertTrue(result);
        assertFalse(studentDao.findAll().contains(student1));
    }

    @Test
    void deleteStudent_WithNonExistingStudent_ShouldReturnFalse() {
        boolean result = studentDao.deleteStudent(student1);
        assertFalse(result);
    }

    @Test
    void deleteStudent_WithNullStudent_ShouldReturnFalse() {
        boolean result = studentDao.deleteStudent(null);
        assertFalse(result);
    }
}
