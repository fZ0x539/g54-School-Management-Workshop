package se.lexicon;

import se.lexicon.data_access.StudentDaoList;

public class Main {
    public static void main(String[] args) {

        Student student1 = new Student("Daniel", "daka@gmail.com", "Järnvägsgatan 1337");
        Course course1 = new Course("Java 101");

        StudentDaoList studentDao = new StudentDaoList();
        studentDao.saveStudent(student1);
        System.out.println(studentDao.findByName("Daniel"));
        System.out.println(studentDao.findByName("Daniele")); //Empty list
        System.out.println(studentDao.findById(1));
        System.out.println(studentDao.findById(0)); // null
        System.out.println(studentDao.findByEmail("daka@gmail.com"));
        System.out.println(studentDao.findByEmail("jaha@gmail.com")); // null

        System.out.println(studentDao.findAll());

        studentDao.deleteStudent(student1);
        System.out.println(studentDao.findAll());

//        course1.register(student1);
//        System.out.println(course1.getStudentList());
//        course1.unregister(student1);
//        System.out.println(course1.getStudentList());
    }
}