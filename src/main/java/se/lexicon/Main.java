package se.lexicon;

import se.lexicon.data_access.CourseDaoList;
import se.lexicon.data_access.StudentDaoList;

public class Main {
    public static void main(String[] args) {

        StudentDaoList studentDao = new StudentDaoList();
        CourseDaoList courseDao = new CourseDaoList();

        Student student1 = new Student("Daniel", "daka@gmail.com", "Järnvägsgatan 1337");
        Student student2 = new Student("Rami", "ramimannen@gmail.com", "Jkpg 301");
        Student student3 = new Student("Gulam", "noxbondy@gmail.com", "Norrköping 13");

        studentDao.saveStudent(student1);
        studentDao.saveStudent(student1);
        studentDao.saveStudent(student2);
        studentDao.saveStudent(student3);
        System.out.println(studentDao.deleteStudent(null));

        Course course1 = new Course("Java Programming 200", studentDao.findAll());
        courseDao.saveCourse(course1);

        System.out.println(courseDao.findById(1));


    }
}