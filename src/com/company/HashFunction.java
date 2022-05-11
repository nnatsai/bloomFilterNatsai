package com.company;

public interface HashFunction {

    /*
    Interface for hashing functions used to create hashes for inputs
    The take in a string inpt and return the hashed integer value
    which will be less that the length of the bit array
     */

    int calculateHash(String s);

}
