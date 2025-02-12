package se.lexicon.data_access;

import se.lexicon.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StudentDaoList implements StudentDao {
    private static List<Student> students = new ArrayList<>();

    @Override
    public Student saveStudent(Student student) {
        if (Objects.nonNull(student)) {
            if (!students.contains(student)) {
                students.add(student);
                System.out.println("Added " + student.getName() + " to StudentDaoList");
                return student;
            } else
                System.out.println(student.getName() + " is already registered to StudentDaoList");
        }
        return null;
    }

    //Returns the student object if found, else null
    @Override
    public Student findByEmail(String email) {
        Student tempStudent = null;
        for (Student student : students) {
            if (student.getEmail().equals(email))
                tempStudent = student;
        }
        if(tempStudent == null)
            System.out.println("Couldn't find any student with that email in the StudentDaoList");
        return tempStudent;
    }


    @Override
    public List<Student> findByName(String name) {
        List<Student> studentsWithMatchingName = new ArrayList<>();
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name))
                studentsWithMatchingName.add(student);
        }
        if(studentsWithMatchingName.isEmpty())
            System.out.println("Couldn't find any student with that name in the StudentDaoList");
        return studentsWithMatchingName;
    }

    @Override
    public Student findById(int id) {
        Student tempStudent = null;
        for (Student student : students) {
            if (student.getId() == id)
                tempStudent = student;
        }
        if(tempStudent == null)
            System.out.println("Couldn't find any student with that id in the StudentDaoList");
        return tempStudent;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean deleteStudent(Student student) {
        if (Objects.nonNull(student)) {
            if (students.contains(student)) {
                students.remove(student);
                System.out.println("Removed " + student.getName() + " from StudentDaoList");
                return true;
            } else
                System.out.println(student.getName() + " isn't registered to StudentDaoList");
        }
        return false;
    }
}
