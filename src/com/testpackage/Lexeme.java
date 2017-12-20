package com.testpackage;

public class Lexeme {
    protected String[] array;
    protected int index = 0;
    private int capacity;
    private String specialSymbol ="*";

    Lexeme() {
        this.capacity = 10;
        this.array = new String[this.capacity];

    }

    public void split(String string) {
        if (isSpecialSymbol(string)) {
            String separator = " ";
            String word;
            for (int i = 0; i < string.length(); i++) {
                boolean isStar = specialSymbol.contains(String.valueOf(string.charAt(i)));
                if (!isStar) {
                    int startInd = findStartIndWord(string, i, separator);
                    int endInd = findEndIndWord(string, startInd, separator);
                    word = string.substring(startInd, endInd);
                    this.add(word);
                    i = endInd-1;
                } else {
                    this.add(specialSymbol);
                }
            }
        } else {
            System.out.println("Символ "+specialSymbol+" не встречается в этой строке.");
        }
    }

    int findStartIndWord(String string, int index, String separator) {
        while (separator.contains(String.valueOf(string.charAt(index)))) {
            index++;
            if (specialSymbol.contains(String.valueOf(string.charAt(index)))){
                this.add("*");
                index++;
            }
        }
        return index;
    }

    int findEndIndWord(String string, int index, String separator) {
        while (!separator.contains(String.valueOf(string.charAt(index)))) {
            index++;
            if (index == string.length()|| specialSymbol.contains(String.valueOf(string.charAt(index)))) {
                break;
            }
        }
        return index;
    }

    void add(String string) {
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

    int length() {
        return this.index;
    }

    boolean isSpecialSymbol(String string) {
        boolean result = false;
        for (int i = 0; i < string.length(); i++) {
            if (specialSymbol.contains(String.valueOf(string.charAt(i)))) {
                result = true;
                break;
            }
        }
        return result;
    }

    String get(int index) {
        return this.array[index];
    }


    void print() {
        String firstWordAfterStar = findFirstWordAfterSpecialSymbol();
        int counter=0;
        for (int i = 0; i < this.length(); i++) {
            if (firstWordAfterStar.equals(this.get(i))) {
                System.out.println(firstWordAfterStar);
            }
            else if (specialSymbol.contains(String.valueOf(this.get(i)))) {
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


    String findFirstWordAfterSpecialSymbol() {
        String result = "";
        for (int i = 0; i < this.length(); i++) {
            if (this.get(i).equals(specialSymbol)) {
                result = this.get(i + 1);
                break;
            }
        }
        return result;
    }
}
