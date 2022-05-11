package com.company;

import static com.company.Constants.BLOOM_FILTER_BIT_ARRAY_LENGTH;

/*/
This is a simple hash function I created which adds the unicode value of the input string
at different indexes, then squares the hash and subtracts its length multiplied by a prime number 53.
The uses XOR to decrease the size of the hash to a value less that the length of the bit array
 */
public class SimpleHashFunction implements HashFunction{

    public int calculateHash(String s){
        int hash = 0;
        if(s.length()>0){
            hash += s.charAt(0);
        }
        if(s.length()>1){
            hash += s.charAt(1);
        }
        if(s.length()>4){
            hash += s.charAt(4);
        }

        hash = (hash * hash ) - (s.length() * 53);

        int mod;
        while (hash > BLOOM_FILTER_BIT_ARRAY_LENGTH) {
            mod = hash % 3;
            hash = hash / 13;
            hash = hash ^ mod;
        }
        return hash;
    }

}
