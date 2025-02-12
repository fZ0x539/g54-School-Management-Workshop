package se.lexicon;

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Daniel", "daka@gmail.com", "Järnvägsgatan 1337");
        Course course1 = new Course("Java 101");
        course1.register(student1);
        System.out.println(course1.getStudentList());
        course1.unregister(student1);
        System.out.println(course1.getStudentList());
    }
}