# bloomFilterNatsai

Here is an implementation of a bloom filter in Java using and the steps I used in my algorithm:

**Part 1: Creating our Bit Array with Hashed values**
1. Take an integer array which can hold 128 values, and initialize it all to 0. 
2. Take a list of words which will act as our set and hash each word with two different hashing functions. 
3. Once the word is hashed, both hashes are reduced to an integer below 128 using XOR. 
4. The reduced hashed values are used to set the corresponding index in the integer array which acts as our bit array. If a value if not set, we set it to 1.

**Part 2: Performing look up**
1. Once the bit array is populated with the corresponding hash values from our set, the next thing is to take user input and check if the user input exists in the set. 
2. The input string we get from the user, is hashed the same way the words in the set were hashed. 
3. Once we have the hash values for the word we check the corresponding indexes of the hashes in the bit array. If all the hashed values’ indexes are set then the string is most likely contained, if not the string is not.

**How to run program:**
1. In the **company** folder run the following command to compile all the files:
   **javac runBloomFilter.java BloomFilter.java HashFunction.java Constants.java ReducedHashFunction.java SimpleHashFunction.java**
2. In the src folder run the following command to run the program:
   **java com.company.runBloomFilter**
3. Enter the string you would like to be hashed and enter. 
4. Output of whether the string you entered will be given.

**Trade Offs:**
I mainly focused on implementing my code in a way that allows it to be easily modified and expanded, in order to give us flexibility in decreasing the false positive rate by either increasing the size of the bloom filter or adding more hash functions, or being able to handle different inputs for different use cases e.g. Not just checking for strings.
1. A larger filter will have less false positives, than a smaller one. So I decided to use 128 bits for the size of the bloom filter. The code is structured in such a way that the size of the filter can easily be changed, to whatever the best size of bloom filter would work for us. 
2. Too few hash functions increase the risk of having more false positives, however too many means that you increase the rate of your bloom filter filling up and potentially being slower. I also decided to use two hash functions which I created myself (mainly due to time constraints improvement see point 1). As a trade off I set up the code using the HashFunction interface to make it easier to add more hash functions to the code as need be. 


**TODOs:**
1. The first Todo would be to fully test the code with junit tests or other testing libraries. (Unfortunately due to time constraints as a result of my schedule, I was unable to complete this part, but under normal circumstances this would be done). 
2. To make this better it would be ideal to use better hashing functions which are independent and uniformly distributed, such as the murmur, xxHash, the fnv series of hashes or the HashMix. 
3. One helpful Todo would be to also add functionality to the program to calculate the false positive rate, which can help us determine what factors we want to change (size of filter and number hash functions used) to get the rate we desire. 
4. Adding more user input validation to ensure that input entered by user to check the set is correct, or that valid characters are entered. This will especially be useful when a specific use case for the bloom filter is identified. e.g. Currently just checking strings, could be modified for usecase to check for valid urls similar to how Google Chrome uses bloom filters.

