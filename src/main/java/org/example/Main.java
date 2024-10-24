package org.example;

public class Main {
    public static int strToInt(String stringy)
    {
        String[] arraying = stringy.split("\\D+");//regex for returning nums
        String valuer = "";
        for (String i : arraying)
        {
            valuer+= i ;
        }

        return Integer.parseInt(valuer);
    }

    public static void main(String[] args) {


    }
}