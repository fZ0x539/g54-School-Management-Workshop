package se.lexicon.data_access;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.lexicon.Course;
import se.lexicon.Student;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCourseDao {
    private CourseDaoList courseDao;
    private Course course1;
    private Course course2;
    private Course course3;
    private static final LocalDate TODAY = LocalDate.now();
    private static final LocalDate TOMORROW = TODAY.plusDays(1);

    @BeforeEach
    void setUp() {
        courseDao = new CourseDaoList();
        // Clear the static list
        courseDao.findAll().clear();

        // Create test courses
        course1 = new Course("Java Programming", TODAY, 10);
        course2 = new Course("Python Programming", TOMORROW, 8);
        course3 = new Course("Java Programming", TOMORROW, 12);
    }

    @Test
    void saveCourse_WithValidCourse_ShouldSaveAndReturnCourse() {
        Course savedCourse = courseDao.saveCourse(course1);
        assertNotNull(savedCourse);
        assertEquals(course1, savedCourse);
        assertTrue(courseDao.findAll().contains(course1));
    }

    @Test
    void saveCourse_WithNullCourse_ShouldReturnNull() {
        Course savedCourse = courseDao.saveCourse(null);
        assertNull(savedCourse);
    }

    @Test
    void saveCourse_WithDuplicateCourse_ShouldNotSaveAndReturnNull() {
        courseDao.saveCourse(course1);
        Course duplicateSave = courseDao.saveCourse(course1);
        assertNull(duplicateSave);
        assertEquals(1, courseDao.findAll().size());
    }

    @Test
    void findById_WithExistingId_ShouldReturnCourse() {
        courseDao.saveCourse(course1);
        Course found = courseDao.findById(course1.getId());
        assertNotNull(found);
        assertEquals(course1, found);
    }

    @Test
    void findById_WithNonExistingId_ShouldReturnNull() {
        Course found = courseDao.findById(999);
        assertNull(found);
    }

    @Test
    void findByName_WithExistingName_ShouldReturnMatchingCourses() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        courseDao.saveCourse(course3);

        List<Course> foundCourses = courseDao.findByName("Java Programming");
        assertEquals(2, foundCourses.size());
        assertTrue(foundCourses.contains(course1));
        assertTrue(foundCourses.contains(course3));
    }

    @Test
    void findByName_WithNonExistingName_ShouldReturnEmptyList() {
        List<Course> foundCourses = courseDao.findByName("Non Existent Course");
        assertTrue(foundCourses.isEmpty());
    }

    @Test
    void findByDate_WithExistingDate_ShouldReturnMatchingCourses() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);
        courseDao.saveCourse(course3);

        List<Course> foundCourses = courseDao.findByDate(TOMORROW);
        assertEquals(2, foundCourses.size());
        assertTrue(foundCourses.contains(course2));
        assertTrue(foundCourses.contains(course3));
    }

    @Test
    void findByDate_WithNonExistingDate_ShouldReturnEmptyList() {
        courseDao.saveCourse(course1);
        List<Course> foundCourses = courseDao.findByDate(TODAY.plusDays(2));
        assertTrue(foundCourses.isEmpty());
    }

    @Test
    void findAll_WithNoCourses_ShouldReturnEmptyList() {
        List<Course> allCourses = courseDao.findAll();
        assertTrue(allCourses.isEmpty());
    }

    @Test
    void findAll_WithMultipleCourses_ShouldReturnAllCourses() {
        courseDao.saveCourse(course1);
        courseDao.saveCourse(course2);

        List<Course> allCourses = courseDao.findAll();
        assertEquals(2, allCourses.size());
        assertTrue(allCourses.contains(course1));
        assertTrue(allCourses.contains(course2));
    }

    @Test
    void removeCourse_WithExistingCourse_ShouldReturnTrueAndRemoveCourse() {
        courseDao.saveCourse(course1);
        boolean result = courseDao.removeCourse(course1);
        assertTrue(result);
        assertFalse(courseDao.findAll().contains(course1));
    }

    @Test
    void removeCourse_WithNonExistingCourse_ShouldReturnFalse() {
        boolean result = courseDao.removeCourse(course1);
        assertFalse(result);
    }

    @Test
    void removeCourse_WithNullCourse_ShouldReturnFalse() {
        boolean result = courseDao.removeCourse(null);
        assertFalse(result);
    }
}
