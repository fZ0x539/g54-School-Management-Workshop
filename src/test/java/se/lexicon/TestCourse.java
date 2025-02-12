package se.lexicon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourse {

    private Course course;
    private Student student1;
    private Student student2;
    private static final String COURSE_NAME = "Java Programming";
    private static final LocalDate START_DATE = LocalDate.now();
    private static final int WEEK_DURATION = 5;

    @BeforeEach
    void setUp() {
        student1 = new Student("John Doe", "john@email.com", "123 Street");
        student2 = new Student("Jane Doe", "jane@email.com", "456 Street");
        course = new Course(COURSE_NAME, START_DATE, WEEK_DURATION);
    }

    @Test
    void constructor_WithNameOnly_ShouldSetDefaultValues() {
        Course courseNameOnly = new Course(COURSE_NAME);
        assertEquals(COURSE_NAME, courseNameOnly.getCourseName());
        assertEquals(LocalDate.now(), courseNameOnly.getStartDate());
        assertEquals(5, courseNameOnly.getWeekDuration());
        assertTrue(courseNameOnly.getStudentList().isEmpty());
    }

    @Test
    void constructor_WithNameAndStudentList_ShouldRegisterStudents() {
        List<Student> students = Arrays.asList(student1, student2);
        Course courseWithStudents = new Course(COURSE_NAME, students);

        assertEquals(COURSE_NAME, courseWithStudents.getCourseName());
        assertEquals(2, courseWithStudents.getStudentList().size());
        assertTrue(courseWithStudents.getStudentList().contains(student1));
        assertTrue(courseWithStudents.getStudentList().contains(student2));
    }

    @Test
    void constructor_WithAllParameters_ShouldSetAllFields() {
        assertEquals(COURSE_NAME, course.getCourseName());
        assertEquals(START_DATE, course.getStartDate());
        assertEquals(WEEK_DURATION, course.getWeekDuration());
        assertTrue(course.getStudentList().isEmpty());
    }

    @Test
    void constructor_ShouldIncrementId() {
        Course course1 = new Course("Course 1");
        Course course2 = new Course("Course 2");
        assertEquals(course1.getId() + 1, course2.getId());
    }

    @Test
    void register_WithValidStudent_ShouldAddToList() {
        course.register(student1);
        assertEquals(1, course.getStudentList().size());
        assertTrue(course.getStudentList().contains(student1));
    }

    @Test
    void register_WithDuplicateStudent_ShouldNotAddAgain() {
        course.register(student1);
        course.register(student1);
        assertEquals(1, course.getStudentList().size());
    }

    @Test
    void register_WithNullStudent_ShouldNotAdd() {
        course.register(null);
        assertTrue(course.getStudentList().isEmpty());
    }

    @Test
    void unregister_WithRegisteredStudent_ShouldRemoveFromList() {
        course.register(student1);
        course.unregister(student1);
        assertFalse(course.getStudentList().contains(student1));
        assertTrue(course.getStudentList().isEmpty());
    }

    @Test
    void unregister_WithNonRegisteredStudent_ShouldNotAffectList() {
        course.register(student1);
        course.unregister(student2);
        assertEquals(1, course.getStudentList().size());
        assertTrue(course.getStudentList().contains(student1));
    }

    @Test
    void unregister_WithNullStudent_ShouldNotAffectList() {
        course.register(student1);
        course.unregister(null);
        assertEquals(1, course.getStudentList().size());
        assertTrue(course.getStudentList().contains(student1));
    }

    @Test
    void setCourseName_ShouldUpdateName() {
        String newName = "Python Programming";
        course.setCourseName(newName);
        assertEquals(newName, course.getCourseName());
    }

    @Test
    void setStartDate_ShouldUpdateDate() {
        LocalDate newDate = LocalDate.now().plusDays(1);
        course.setStartDate(newDate);
        assertEquals(newDate, course.getStartDate());
    }

    @Test
    void setWeekDuration_ShouldUpdateDuration() {
        int newDuration = 10;
        course.setWeekDuration(newDuration);
        assertEquals(newDuration, course.getWeekDuration());
    }

    @Test
    void toString_ShouldReturnCorrectFormat() {
        course.register(student1);
        String expected = "Course{id=" + course.getId() + ", name='" + COURSE_NAME + "', students=[" + student1.toString() + "]}";
        assertEquals(expected, course.toString());
    }
}