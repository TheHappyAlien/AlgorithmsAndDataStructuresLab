package com.company;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Main {

    Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
	// write your code here

//        CodeSwap codeSwap = new CodeSwap();
//
//        codeSwap.pairSwap(" _asgjh=Shu3 (0)=1 a=b=_c 78a78shdg ashbdy 8872asjhdJ=(asjlkdh kh=_9nsJ kskj dddd asd kkkk asjdba");

//      drawAPyramid(-10, -10);
//        System.out.println("\n");
//        drawAFigure(0);

        drawEmptyPyramids(1);
    }

    private static String drawARow(int nrOfXs, int nrOfSpaces){

        StringBuilder stringBuilder = new StringBuilder();

        //Spaces at the beginning.

        for (int i = 0; i < nrOfSpaces; i++) {
            stringBuilder.append(" ");
        }

        //Drawing in the step.

        for (int j = 0; j < nrOfXs; j++) {
            stringBuilder.append("X");
        }

        //Spaces at the end.

        for (int k = 0; k < nrOfSpaces; k++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }

    private static void drawAPyramid(int n, int h){

        if (n >= 0){

        int numberOfXs = (2*n)+1;
        int numberOfSpaces = h-1;

        for (int row = 0; row < h; row++) {

            System.out.println(drawARow(numberOfXs, numberOfSpaces));

            numberOfXs += 2;
            numberOfSpaces--;
        }
        }else {
            System.out.println("n cannot be negative.");
        }

    }

    private static void drawAFigure(int n){

        boolean bold = true;

        for (int segment = 0; segment <= n; segment++){

            if (bold){
                //Set all subsequent text to bold.
                System.out.print("\033[0;1m");
            }else {
                //Format reset.
                System.out.print("\u001b[0m");
            }

//            for (int row = 0; row < n-segment; row++){
//
//                int nrOfXs = (2 * (row + segment)) + 1;
//                int nrOfSpaces = n - (row + segment + 1);
//
//                System.out.println(drawARow(nrOfXs, nrOfSpaces));

            drawAPyramid(segment, n-segment);

            //}
            bold = !bold;
        }

    }



    private static void drawEmptyPyramids(int n){

        int spaceInBetween = n-3;
        int spaceOnTheSides = 1;

        for (int i = 0; i < 2*n+1; i++){
            System.out.print("X");
        }

        System.out.println();

        while (spaceInBetween > 0){

            drawEmptyPyramidLine(spaceOnTheSides, spaceInBetween);

            spaceInBetween -= 2;
            spaceOnTheSides ++;

        }

        drawEmptySpace(spaceOnTheSides);
        for (int i = 0; i < (spaceInBetween) + 2; i++) {
            System.out.print("X");
        }

        drawEmptySpace(2 * spaceOnTheSides - 1);

        for (int i = 0; i < (spaceInBetween) + 2; i++) {
            System.out.print("X");
        }
        drawEmptySpace(spaceOnTheSides);

        System.out.println();

        if (spaceInBetween == -1){
            spaceInBetween +=2;
            spaceOnTheSides--;
        }

        while (spaceInBetween <= n-3){

            drawEmptyPyramidLine(spaceOnTheSides, spaceInBetween);

            spaceInBetween += 2;
            spaceOnTheSides --;
        }

        for (int i = 0; i < 2*n+1; i++){
            System.out.print("X");
        }
    }

    private static void drawEmptySpace(int numberOfSpaces){
        for (int i = 0; i < numberOfSpaces; i++){
            System.out.print(" ");
        }
    }

    private static void drawEmptyPyramidLine(int spaceOnTheSides, int spaceInBetween){
        drawEmptySpace(spaceOnTheSides);

        System.out.print("X");

        drawEmptySpace(spaceInBetween);

        System.out.print("X");

        drawEmptySpace(2 * spaceOnTheSides - 1);

        System.out.print("X");

        drawEmptySpace(spaceInBetween);

        System.out.print("X");

        drawEmptySpace(spaceOnTheSides);


        System.out.println();
    }

}
