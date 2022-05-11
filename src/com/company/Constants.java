package com.company;

/*

 */
public class Constants {

    //Private constructor prevents this constants class from being instantiated
    private Constants() {
    }

    //length of the bloom filter bit array can easily be changed here depending one the size we would like to use
    public static final int BLOOM_FILTER_BIT_ARRAY_LENGTH = 128;
}
