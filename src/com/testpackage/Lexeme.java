package com.testpackage;

public class Lexeme {
    private String[] array;
    private int index = 0;
    private int capacity;
    private String star="*";

    Lexeme() {
        this.capacity = 10;
        this.array = new String[this.capacity];

    }

    public void split(String string) {
        if (isStar(string)) {
            String separator = " ";
            String word;
            for (int i = 0; i < string.length(); i++) {
                boolean isStar = star.contains(String.valueOf(string.charAt(i)));
                if (!isStar) {
                    int startInd = findStartIndWord(string, i, separator);
                    int endInd = findEndIndWord(string, startInd, separator);
                    word = string.substring(startInd, endInd);
                    this.add(word);
                    i = endInd-1;
                } else {
                    this.add(star);
                }
            }
        } else {
            System.out.println("Символ '*' не встречается в этой строке.");
        }
    }

    private int findStartIndWord(String string, int index, String separator) {
        while (separator.contains(String.valueOf(string.charAt(index)))) {
            index++;
            if (star.contains(String.valueOf(string.charAt(index)))){
                this.add("*");
                index++;
            }
        }
        return index;
    }

    private int findEndIndWord(String string, int index, String separator) {
        while (!separator.contains(String.valueOf(string.charAt(index)))) {
            index++;
            if (index == string.length()|| star.contains(String.valueOf(string.charAt(index)))) {
                break;
            }
        }
        return index;
    }

    private void add(String string) {
        if (index <= array.length - 1) {
            array[index] = string;
            index++;
        } else {
            this.copy();
            array[this.index] = string;
            index++;
        }
    }

    private void copy() {
        String newArray[] = new String[array.length + 1];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
        }
        this.array = newArray;
    }

    private int length() {
        return this.index;
    }

    private boolean isStar(String string) {
        boolean result = false;
        for (int i = 0; i < string.length(); i++) {
            if (star.contains(String.valueOf(string.charAt(i)))) {
                result = true;
                break;
            }
        }
        return result;
    }

    private String get(int index) {
        return this.array[index];
    }


    public void print() {
        String firstWordAfterStar = findFirstWordAfterStar();
        int counter=0;
        for (int i = 0; i < this.length(); i++) {
            if (firstWordAfterStar.equals(this.get(i))) {
                System.out.println(firstWordAfterStar);
            }
            else if (star.contains(String.valueOf(this.get(i)))) {
                break;
            }
            else {
                counter++;
            }
        }
        if(counter==this.length()-2){
            System.out.println("Таких слов не найдено.");
        }
    }


    private String findFirstWordAfterStar() {
        String result = "";
        for (int i = 0; i < this.length(); i++) {
            if (this.get(i).equals("*")) {
                result = this.get(i + 1);
                break;
            }
        }
        return result;
    }
}
