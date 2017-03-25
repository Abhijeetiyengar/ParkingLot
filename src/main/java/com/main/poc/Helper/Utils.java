package com.main.poc.Helper;

import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class Utils {
    static Scanner scan = new Scanner(System.in);

    public static int convertStringToInt(String str)
    {
        return  Integer.parseInt(str);
    }

    public static <T> T getInput(String message, Function<String,T> function)
    {


        Object value=null;

        while (value==null)
        {
            System.out.println(message+"\n");

            String s = scan.nextLine();

            value=function.apply(s);

            if(value==null)
                System.out.println("Wrong Value selected  , Please re-insert your value");
        }



        return  (T)value;
    }
}
