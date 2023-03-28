package com.company;

import java.util.Comparator;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
	// write your code here

        Comparator<Integer> integerComparator = new IntegerComparator();
        Comparator<Student> studentComparator = new StudentComparator();
        HashFunction<Integer> integerHashFunction = new IntegerHashFunction();
        HashFunction<Student> studentHashFunction = new StudentHashFunction();
        IncrementalFunction<Integer> linearProbing = new LinearProbing<>(integerHashFunction);
        IncrementalFunction<Student> linearStudentProbing = new LinearProbing<>(studentHashFunction);
        IncrementalFunction<Integer> quadraticProbing = new QuadraticProbing<>(integerHashFunction);
        double maxLoad = 0.5;

        OpenAddressingHashTable<Integer> hashTable = new OpenAddressingHashTable<Integer>(maxLoad, integerComparator, linearProbing, 10);
        OpenAddressingHashTable<Integer> hashTable1 = new OpenAddressingHashTable<Integer>(maxLoad, integerComparator, quadraticProbing, 10);
        SeparateChainingHashTable<Integer> hashTable2 = new SeparateChainingHashTable<Integer>(maxLoad, integerComparator, integerHashFunction, 10);
        OpenAddressingHashTable<Student> hashTable3 = new OpenAddressingHashTable<Student>(maxLoad, studentComparator, linearStudentProbing, 10);

        Random random = new Random();

//        for (int i = 0; i < 2000000; i++){
//            int input = random.nextInt();
//            hashTable.insert(input);
//            hashTable1.insert(input);
//            hashTable2.insert(input);
//        }

        int[] grades1 = new int[]{20, 10, 15, 2, 1};
        int[] grades2 = new int[]{19, 50, 11, 3, 1};

        hashTable3.insert(new Student("Imie", "Nazwisko", 20, grades1));
        hashTable3.insert(new Student("Imie", "Nazwisko", 20, grades1));
        hashTable3.insert(new Student("Imie", "Nazwisko", 50, grades1));
        hashTable3.insert(new Student("LepszeImie", "Nazwisko", 50, grades1));
        hashTable3.insert(new Student("LepszeImie", "MasneNazwisko", 50, grades1));
        hashTable3.insert(new Student("LepszeImie", "MasneNazwisko", 50, grades2));


        System.out.println("Open addressing - linear:");

        System.out.println(hashTable3);

        System.out.println("LF : " + hashTable3.loadFactor());
        System.out.println("HE : " + hashTable3.hashFunctionEvaluations());
        System.out.println("CO : " + hashTable3.collisions());
        System.out.println("IC : " + hashTable3.insertComparisons());
        System.out.println("------------------");

//        System.out.println("Open addressing - linear:");
//        System.out.println("LF : " + hashTable.loadFactor());
//        System.out.println("HE : " + hashTable.hashFunctionEvaluations());
//        System.out.println("CO : " + hashTable.collisions());
//        System.out.println("IC : " + hashTable.insertComparisons());
//        System.out.println("------------------");
//
//        System.out.println("Open addressing - quadratic:");
//        System.out.println("LF : " + hashTable1.loadFactor());
//        System.out.println("HE : " + hashTable1.hashFunctionEvaluations());
//        System.out.println("CO : " + hashTable1.collisions());
//        System.out.println("IC : " + hashTable1.insertComparisons());
//        System.out.println("------------------");
//
//        System.out.println("Separate chain:");
//        System.out.println("LF : " + hashTable2.loadFactor());
//        System.out.println("HE : " + hashTable2.hashFunctionEvaluations());
//        System.out.println("CO : " + hashTable2.collisions());
//        System.out.println("IC : " + hashTable2.insertComparisons());

    }
}
