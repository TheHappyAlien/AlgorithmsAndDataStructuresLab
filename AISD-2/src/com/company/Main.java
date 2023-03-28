package com.company;

public class Main {

    public static void main(String[] args){
        FiniteSeries<String> stringSeries = new FiniteSeries<>(new StringGenerator("S"), 20);

        for (String string : stringSeries){
            System.out.println(string);
        }

        System.out.println("\n/-------------------------------------------------------------------------------------/\n");

        Series<Integer> integerSeries = new Series<>(new SquareGenerator());

        for (Integer integer : integerSeries){
            System.out.println(integer);
        }
        System.out.println("\n/-------------------------------------------------------------------------------------/\n");



        FiniteSeries<Integer> integerFiniteSeries = new FiniteSeries<>(new IntegerGenerator(3), 10);

        for (Integer integer : integerFiniteSeries){
            System.out.println(integer);
        }

    }
}
