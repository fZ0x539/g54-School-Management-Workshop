package se.lexicon.data_access;

import se.lexicon.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentDaoList implements StudentDao {
    private static List<Student> students = new ArrayList<>();


    @Override
    public Student saveStudent(Student student) {
        return null;
    }

    @Override
    public Student findByEmail(String email) {
        return null;
    }

    @Override
    public List<Student> findByName(String name) {
        return List.of();
    }

    @Override
    public Student findById(int id) {
        return null;
    }

    @Override
    public List<Student> findAll() {
        return List.of();
    }

    @Override
    public boolean deleteStudent(Student student) {
        return false;
    }
}
