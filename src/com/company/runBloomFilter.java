package com.company;
import java.io.*;

public class runBloomFilter {
/*
This class takes in user input as a word and runs the bloomFilter algorithm
to check if the string inputted by the user may be contained in the set
 */
    public static void main(String[] args) throws IOException {
        BufferedReader userInputReader = new BufferedReader(
                new InputStreamReader(System.in));
        System.out.print("Enter a word to check to see if it is contained in the set: ");
        String userInput = userInputReader.readLine();

        BloomFilter bloom = new BloomFilter();

        if (bloom.isElementContained(userInput)) {
            System.out.println("The input you entered is possibly contained in the set");
        } else {
            System.out.println("The input you entered is not contained in the set");
        }
    }
}

