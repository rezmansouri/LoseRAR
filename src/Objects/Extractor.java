/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * std_reza_mansouri@khu.ac.ir
 */
package Objects;

import Collections.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Extractor {
    private final byte ZERO = -128, ONE = 64, TWO = 32, THREE = 16, FOUR = 8, FIVE = 4, SIX = 2, SEVEN = 1;
    private Zip zip;

    public Extractor(String inputFileAddress) throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(inputFileAddress));
        zip = (Zip) objectInputStream.readObject();
    }

    public String extract() {
        String decoded = "";
        String temp = "";
        ArrayMap<String, Character> dictionary = zip.getDictionary();
        int readBits = 0;
        int numberOfBits = zip.getNumberOfBits();
        byte[] huffmanCode = zip.getHuffmanCode();
        int indexOfByteArray = 0;
        MAIN_LOOP:
        while (readBits != numberOfBits) {
            byte currentByte = huffmanCode[indexOfByteArray];
            for (int j = 0; j < 8; j++) {
                if (readBits == numberOfBits) break MAIN_LOOP;
                byte scanner = 0;
                switch (j) {
                    case 0:
                        scanner = ZERO;
                        break;
                    case 1:
                        scanner = ONE;
                        break;
                    case 2:
                        scanner = TWO;
                        break;
                    case 3:
                        scanner = THREE;
                        break;
                    case 4:
                        scanner = FOUR;
                        break;
                    case 5:
                        scanner = FIVE;
                        break;
                    case 6:
                        scanner = SIX;
                        break;
                    case 7:
                        scanner = SEVEN;
                        break;
                }
                byte scanned = (byte) (currentByte & scanner);
                readBits++;
                if (scanned != 0) temp += "1";
                else temp += "0";
                Character translated = dictionary.get(temp);
                if (translated != null) {
                    decoded += translated;
                    temp = "";
                }
            }
            indexOfByteArray++;
        }
        return decoded;
    }
}
