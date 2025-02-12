package se.lexicon;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Course {
    private static int refId = 0;
    private int id;
    private String courseName;
    private LocalDate startDate;
    private int weekDuration;
    private List<Student> students = new ArrayList<>();

    //Constructor(s)
    public Course(String courseName, List<Student> students){
        this(courseName);
        for(Student student : students){
            this.register(student);
        }
    }

    public Course(String courseName) {
        this(courseName, LocalDate.now(), 5);
    }

    public Course(String courseName, LocalDate startDate, int weekDuration) {
        this.id = ++refId;
        setCourseName(courseName);
        setStartDate(startDate);
        setWeekDuration(weekDuration);
    }

    //Getters & Setters
    public int getId() {
        return id;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getWeekDuration() {
        return weekDuration;
    }

    public void setWeekDuration(int weekDuration) {
        this.weekDuration = weekDuration;
    }

    public List<Student> getStudentList(){
        return students;
    }

    //Methods
    public void register(Student student) {
        if (Objects.nonNull(student)) {
            if (!students.contains(student)) {
                students.add(student);
                System.out.println("Registered " + student.getName() + " to " + getCourseName());
            } else
                System.out.println(student.getName() + " is already registered to this course");
        }
    }

    public void unregister(Student student) {
        if (Objects.nonNull(student)) {
            if (students.contains(student)) {
                students.remove(student);
                System.out.println("Unregistered " + student.getName() + " from " + getCourseName());
            }
        }
    }

    @Override
    public String toString() {
        return "Course{id=" + id + ", name='" + getCourseName() + "', students=" + students + "}";
    }
}
