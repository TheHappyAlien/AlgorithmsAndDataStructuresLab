package com.company;

public class StudentHashFunction implements HashFunction<Student>{

    @Override
    public int hashCode(Student student) {
        if (student != null) {
            return student.hashCode();
        }else return 0;
    }

}
