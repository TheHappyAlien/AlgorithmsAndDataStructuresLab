package com.company;

import java.util.*;

public class CodeSwap {

    Map<Integer, String> ids = new HashMap<>();

    ArrayList<Character> id1Chars = new ArrayList<>();
    ArrayList<Character> id2Chars = new ArrayList<>();

    boolean id1Valid = false;
    boolean id2Valid = false;

    public void pairSwap(String txt){

        if (txt != null) {

           if (txt.trim().equals("")) {
               System.out.println(txt);
           }else {

               ArrayList<String> miscellaneousText = new ArrayList<>();

               String str = txt;

               int idPlacementNumber = 0;

               while (str.length() > 0) {
                   str = searchForPotentialId(str);
                   str = checkIdValidity(str, true, id1Chars);
                   str = checkForSecondId(str);
                   if (id1Valid && id2Valid) {
                       ids.put(idPlacementNumber, idString(id1Chars));
                       idPlacementNumber++;
                       ids.put(idPlacementNumber, idString(id2Chars));
                       idPlacementNumber++;
                   }

                   id1Chars.clear();
                   id2Chars.clear();

                   id1Valid = false;
                   id2Valid = false;

               }

               str = txt;

               if (idPlacementNumber > 0) {

                   String[] temp = new String[2];

                   for (int i = 0; i < idPlacementNumber; i++) {

                       temp = str.split(ids.get(i), 2);
                       miscellaneousText.add(temp[0]);
                       str = temp[1];
                   }

                   miscellaneousText.add(temp[1]);

                   StringBuilder stringBuilder = new StringBuilder();

                   for (int i = 0; i < idPlacementNumber / 2; i++) {
                       stringBuilder.append(miscellaneousText.get(i * 2)).append(ids.get((2 * i) + 1));
                       stringBuilder.append(miscellaneousText.get((i * 2) + 1)).append(ids.get(2 * i));
                   }
                   stringBuilder.append(miscellaneousText.get(miscellaneousText.size() - 1));

                   System.out.println(stringBuilder.toString());

               } else System.out.println(txt);
           }
        }else System.out.println("There was no text provided");

    }

    private String idString(ArrayList<Character> idChars){

        StringBuilder idBuilder = new StringBuilder();

        for (Character character:idChars){
            idBuilder.append(character);
        }

        return idBuilder.toString();
    }

    private String searchForPotentialId(String string){

        String str = string;

        for (int i = 0; i < string.length(); i++){
            if (str.substring(0, 1).matches("[a-zA-Z]+") || str.startsWith("_")){
                break;
            }
            str = str.substring(1);
        }

        return str;

    }

    private String checkIdValidity(String string, boolean isItId1, ArrayList<Character> idChars){

        String str = string;

        //Cutting off the underscore before checking the rest of the id.
        if (str.startsWith("_")){
            idChars.add('_');
            str = str.substring(1);
        }

        for (int i = 0; i < string.length(); i++){
            if (str.substring(0, 1).matches("[a-zA-Z]+") || str.substring(0, 1).matches("[0123456789]")){
                idChars.add(str.charAt(0));
                if (isItId1){
                    id1Valid = true;
                }else {
                    id2Valid = true;
                }
            }else {
                break;
            }
            str = str.substring(1);
        }

        return str;

    }

    private String checkForSecondId(String string){

        String str = string;

        //See if there is an equal sign after first id.
        if (str.startsWith("=")){
            str = str.substring(1);
            //See if there is a potential id after the equal sign.
            if (str.substring(0, 1).matches("[a-zA-Z]+") || str.startsWith("_")){
                //Check if id2 is valid, if so save it to id2Chars.
               str = checkIdValidity(str, false, id2Chars);
            }
        }

        return  str;
    }

}
