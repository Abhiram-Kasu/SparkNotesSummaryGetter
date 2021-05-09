package com.soap.sparknotesfinal.selenium;

import java.util.Scanner;

public class Input {

    public static int getInput(String output, int a){
        Scanner scanner = new Scanner(System.in);
        System.out.println(output);
        return scanner.nextInt();

    }
    public static double getInput(String output, double a){
        Scanner scanner = new Scanner(System.in);
        System.out.println(output);
        return scanner.nextDouble();

    }
    public static String getInput(String output, String a){
        Scanner scanner = new Scanner(System.in);
        System.out.println(output);
        return scanner.nextLine();

    }
}
