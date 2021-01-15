public class Student implements Comparable<Student> {
    public String name;
    public int age;

    public Student(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student otherStudent) {

        if (this.name.compareTo(otherStudent.name) == -1) {
            return -1;
        }
        else if (this.name.compareTo(otherStudent.name) == 1) {
            return 1;
        }
        else {
            return 0;
        }

    }

}
