package com.main.poc.Helper;

import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class Utils {
    static Scanner scan = new Scanner(System.in);

    /**
     Convert String to int
     */

    public static int convertStringToInt(String str)
    {
        return  Integer.parseInt(str);
    }

    /**
     * Provide function which would be a validator to continue taking input . Function should return null if its not valid.
     *
     * @param message
     * @param function
     * @param <T>
     * @return
     */

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
