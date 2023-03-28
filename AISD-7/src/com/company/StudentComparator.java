package com.company;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {

        if (s1 != null && s2 != null) {

            int lastNameComp = s1.getLastName().compareTo(s2.getLastName());
            if (lastNameComp != 0) {
                return lastNameComp;
            }

            int nameComp = s1.getName().compareTo(s2.getName());
            if (nameComp != 0) {
                return nameComp;
            }

            if (s1.getAge() != s2.getAge()){
                return Integer.compare(s1.getAge(), s2.getAge());
            }

            if (s1.getGrades().length != s2.getGrades().length){
                return Integer.compare(s1.getGrades().length, s2.getGrades().length);
            }

            for (int gradeIndex = 0; gradeIndex < s1.getGrades().length; gradeIndex++){
                if (s1.getGrades()[gradeIndex] != s2.getGrades()[gradeIndex]){
                    return Integer.compare(s1.getGrades()[gradeIndex],s2.getGrades()[gradeIndex]);
                }
            }

        } else throw new IllegalArgumentException("Cant compare a null student");

        return 0;
    }
}
