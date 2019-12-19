package com.yydcyy.sort.selection;


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

    @Override
    public int compareTo(Student o) {

        if (this.score > o.score) return 1;
        else if (this.score < o.score) return -1;
        else {
            return this.name.compareTo(o.name);
        }
    }

    @Override
    public String toString() {
        return "Student: " + this.name + Integer.toString(this.score);
    }
}
