package com.testpackage;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Введите строку:");
        String input=scanner.nextLine();
        Lexeme lexemeArray=new Lexeme();
        lexemeArray.split(input);
        lexemeArray.print();
    }
}
