package com.company;

import java.util.Arrays;
import java.util.Objects;

public class Student {

    private final String name;
    private final String lastName;
    private int age;
    private int[] grades;

    public Student(String name, String lastName, int age, int[] grades) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.grades = grades;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int[] getGrades() {
        return grades;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    @Override
    public String toString(){
        return lastName + " " + name + " | " + age;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null){
            return false;
        }

        if (o instanceof Student){
            Student s2 = (Student) o;

            if (lastName.compareTo(s2.getLastName()) != 0) {
                return false;
            }else

            if (name.compareTo(s2.getName()) != 0) {
                return false;
            } else

            if (age != s2.getAge()){
                return false;
            } else

            if (grades.length != s2.getGrades().length){
                return false;
            }

            for (int gradeIndex = 0; gradeIndex < grades.length; gradeIndex++){
                if (grades[gradeIndex] != s2.getGrades()[gradeIndex]){
                    return false;
                }
            }
        }else return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, age);
    }

}
