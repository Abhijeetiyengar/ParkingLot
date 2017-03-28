package com.main.poc.helper;

import java.util.Scanner;
import java.util.function.Function;

/**
 * Created by abhijeetiyengar on 3/25/17.
 */
public class InputOutputHelper {
    static Scanner scan = new Scanner(System.in);

    /**
     * Provide function which would be a validator to continue taking input . Function should return null if its not valid.
     *
     * @param message
     * @param function
     * @param <T>
     * @return
     */

    public static <T> T getInput(String message, Function<String, T> function) {

        Object value = null;

        while (value == null) {
            InputOutputHelper.printOutput(message + "\n");

            String s = scan.nextLine();

            value = function.apply(s);

            if (value == null)
                InputOutputHelper.printOutput("Wrong Value selected  , Please re-insert your value");
        }

        return (T) value;
    }

    public static void printOutput(String msg) {
        System.out.println(msg);
    }

}
