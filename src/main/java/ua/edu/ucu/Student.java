package ua.edu.ucu;


class Student {

    private double GPA;
    private int year;
    private String name;
    private String surname;

    public Student(String name, String surname, double GPA, int year) {
        this.GPA = GPA;
        this.year = year;
        this.name = name;
        this.surname = surname;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + surname.hashCode() + year * 2 + (int) GPA * 2;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Student) {
            return hashCode() == obj.hashCode();
        }
        else {
            return false;
        }
    }

    public double getGPA() {
        return GPA;
    }

    public int getYear() {
        return year;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return "Student{name=" + name + ", surname=" + surname + ", " + "GPA=" + GPA + ", year=" + year + '}';
    }

}
