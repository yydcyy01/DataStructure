package cn.yydcyy.array;

/**
 * Created by YYDCYY on 2019-08-12.
 */
public class Student implements Comparable<Student> {
    private String name;
    private int score;

    public Student(String studentName, int studentScore) {
        name = studentName;
        score = studentScore;
    }

    public static void main(String[] args) {
        Array<Student> arr = new Array<>();
        arr.addFirst(new Student("Alice", 100));
        arr.addFirst(new Student("Bob", 66));
        arr.addFirst(new Student("Charlie", 88));
        System.out.println(arr);
    }

    @Override
    public String toString() {
        return String.format("com.yydcyy.array.Student(name : %s, score : %d)", this.name, this.score);
    }

    @Override
    public int compareTo(Student o) {

        if (this.score > o.score) return 1;
        else if (this.score < o.score) return -1;
        else {
            return this.name.compareTo(o.name);
        }
    }
}
