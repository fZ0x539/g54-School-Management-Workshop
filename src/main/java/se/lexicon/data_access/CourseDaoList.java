package se.lexicon.data_access;

import se.lexicon.Course;
import se.lexicon.Student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseDaoList implements CourseDao {
    private static List<Course> courses = new ArrayList<>();

    @Override
    public Course saveCourse(Course course) {
        if (Objects.nonNull(course)) {
            if (!courses.contains(course)) {
                courses.add(course);
                System.out.println("Added " + course.getCourseName() + " to CourseDaoList");
                return course;
            } else
                System.out.println(course.getCourseName() + " is already added to CourseDaoList");
        }
        return null;
    }

    @Override
    public Course findById(int id) {
        Course tempCourse = null;
        for (Course course : courses) {
            if (course.getId() == id)
                tempCourse = course;
        }
        if(tempCourse == null)
            System.out.println("Couldn't find any course with that id in the CourseDaoList");
        return tempCourse;
    }

    @Override
    public List<Course> findByName(String name) {
        List<Course> matchingCourses = new ArrayList<>();
        for(Course course : courses){
            if(course.getCourseName().equalsIgnoreCase(name))
                matchingCourses.add(course);
        }
        if(matchingCourses.isEmpty())
            System.out.println("Couldn't find any courses with that name in the CourseDaoList");
        return matchingCourses;

    }

    @Override
    public List<Course> findByDate(LocalDate date) {
        List<Course> coursesWithMatchingStartDate = new ArrayList<>();
        for(Course course : courses){
            if(course.getStartDate().equals(date))
                coursesWithMatchingStartDate.add(course);
        }
        if(coursesWithMatchingStartDate.isEmpty())
            System.out.println("Couldn't find any courses with that start date");
        return coursesWithMatchingStartDate;
    }

    @Override
    public List<Course> findAll() {
        return courses;
    }

    @Override
    public boolean removeCourse(Course course) {
        if (Objects.nonNull(course)) {
            if (courses.contains(course)) {
                courses.remove(course);
                System.out.println("Removed " + course.getCourseName() + " from CourseDaoList");
                return true;
            } else
                System.out.println(course.getCourseName() + " doesn't exist in CourseDaoList");
        }
        return false;
    }
}
