/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * std_reza_mansouri@khu.ac.ir
 */
package Objects;

import Collections.ArrayMap;

import java.io.Serializable;

public class Zip implements Serializable {
    ArrayMap<String, Character> dictionary;
    private byte[] huffmanCode;
    private int numberOfBits;
    public Zip(ArrayMap<String, Character> dictionary, byte[] huffmanCode, int numberOfBits) {
        this.dictionary = dictionary;
        this.huffmanCode = huffmanCode;
        this.numberOfBits = numberOfBits;
    }

    public int getNumberOfBits() {
        return numberOfBits;
    }

    public byte[] getHuffmanCode() {
        return huffmanCode;
    }

    public ArrayMap<String, Character> getDictionary() {
        return dictionary;
    }
}
