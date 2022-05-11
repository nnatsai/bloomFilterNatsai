package com.company;

import static com.company.Constants.BLOOM_FILTER_BIT_ARRAY_LENGTH;
/*
This hash function uses the java built in hash function and then reduces the value
to an integer less that the length of the bit array length using XOR and modulus.
 */
public class ReducedHashFunction implements HashFunction{

    public int calculateHash(String s) {
        //We switch off the sign bit, to ensure that the numbers generated are positive
        int hash = s.hashCode() & 0xfffffff;
        int mod;

        while (hash > BLOOM_FILTER_BIT_ARRAY_LENGTH) {
            mod = hash % 13;
            hash = hash / 10;
            hash = hash ^ mod;
        }
        return hash;

    }
}
