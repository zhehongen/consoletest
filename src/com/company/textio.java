package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class textio {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter output = new PrintWriter("temp.txt");
        output.print("Java 101");
        output.close();
        Scanner input = new Scanner(new File("temp.txt"));
        System.out.println(input.nextLine());
    }
}
