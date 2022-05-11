package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.company.Constants.BLOOM_FILTER_BIT_ARRAY_LENGTH;

public class BloomFilter {
    private ReducedHashFunction reducedHash;
    private SimpleHashFunction simpleHash;
    private int[] bloomFilterDictionary;

    public BloomFilter() throws IOException {
        reducedHash = new ReducedHashFunction();
        simpleHash = new SimpleHashFunction();
        bloomFilterDictionary =  createDictionary();
    }

    /*
    This method creates our set from reading and hashing words from a txt file
    The hashed values are then mapped to their corresponding indexes in the 64 bit array
     */
    private int[] createDictionary() throws IOException {
        //Using 64 bit array
        int[] bitArray = new int[BLOOM_FILTER_BIT_ARRAY_LENGTH];

        //reading input from txt file with 1000 most common words in English
        File inputSet = new File("dictionary.txt");
        BufferedReader bufferInput = new BufferedReader(new FileReader(inputSet));

        String input;

        int reducedHashValue;
        int simpleHashValue;
        while ((input = bufferInput.readLine()) != null) {

            reducedHashValue = reducedHash.calculateHash(input);
            simpleHashValue = simpleHash.calculateHash(input);

            int reducedHashIndex = reducedHashValue;
            int simpleHashIndex = simpleHashValue;

            //If the returned hash value is already 0 we keep it as 0 when entering the value in the bitArray
            if(reducedHashValue != 0){
                reducedHashIndex = reducedHashValue - 1;
            }
            if(simpleHashValue != 0){
                simpleHashIndex = simpleHashValue - 1;
            }
            //if the value of the hashes is not already set to the corresponding index we set it
            if(bitArray[reducedHashIndex] == 0){
                bitArray[reducedHashIndex] = 1;
            }
            if(bitArray[simpleHashIndex] == 0){
                bitArray[simpleHashIndex] = 1;
            }
        }
        return bitArray;
    }

    /*
    Here we are checking to see if the element from the user input is contained in the set we have
    by first hashing the input string and then checking the corresponding indexes in the 64 bit array
     */
    public boolean isElementContained(String userInput) throws IOException {

        int reducedHashValue = reducedHash.calculateHash(userInput);
        int simpleHashValue = simpleHash.calculateHash(userInput);

        int reducedHashIndex = reducedHashValue;
        int simpleHashIndex = simpleHashValue;

        //If the returned hash value is already 0 we keep it as 0 when entering the value in the bitArray
        if(reducedHashIndex != 0){
            reducedHashIndex = reducedHashValue - 1;
        }
        if(simpleHashIndex != 0){
            simpleHashIndex = simpleHashValue - 1;
        }

        boolean reducedHashMatchesInputIndex = bloomFilterDictionary[reducedHashIndex]==1;
        boolean simpleHashMatchesInputIndex = bloomFilterDictionary[simpleHashIndex]==1;

        //Check if the bits for the corresponding hashes to the hashes are set
        if(reducedHashMatchesInputIndex && simpleHashMatchesInputIndex){
            return true;
        }
        return false;
    }

}
