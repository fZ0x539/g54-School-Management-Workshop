package se.lexicon;

public class Student {
    private static int refId = 0;
    private int id;
    private String name;
    private String email;
    private String address;

    //Constructor(s)
    public Student( String name, String email, String address){
        this.id = ++refId;
        setName(name);
        setEmail(email);
        setAddress(address);
    }

    //Getters & Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString(){
        return "Student{id=" + id + ", name='" + name + "'}";
    }

}
