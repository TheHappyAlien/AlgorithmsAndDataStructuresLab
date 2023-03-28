package com.company;

import java.util.ArrayList;

public class StringGenerator implements SeriesGenerator<String>{

    private String message;

    public StringGenerator(String message){
        this.message = message;
    }

    @Override
    public String generate(int n) {
        String temp = "";
        for (int i = 0; i < n; i++){
            temp = temp.concat(message);
        }
        return temp;
    }
}
