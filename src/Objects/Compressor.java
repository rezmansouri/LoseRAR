/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * std_reza_mansouri@khu.ac.ir
 */
package Objects;

import Collections.ArrayMap;
import Collections.Node;
import Collections.PriorityQueue;

import java.io.*;
import java.util.Set;

public class Compressor {
    private String parameter;
    private final byte ZERO = -128, ONE = 64, TWO = 32, THREE = 16, FOUR = 8, FIVE = 4, SIX = 2, SEVEN = 1;
    private Zip zip;

    public Compressor(String parameter) {
        this.parameter = parameter;
    }

    public Compressor(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        String p = "";
        while (line != null) {
            p += line;
            line = reader.readLine();
        }
        reader.close();
        parameter = p;
    }

    public void compress(String outputFileAddress) throws PriorityQueue.QueueOverflowedException, PriorityQueue.EmptyQueueException, IOException {
        ArrayMap<Character, String> dictionary = getDictionary();
        byte[] huffmanCode = {};
        int numberOfBits = 0;
        for (int i = 0; i < parameter.length(); i++) {
            char currentCharacter = parameter.charAt(i);
            String code = dictionary.get(currentCharacter);
            for (int j = 0; j < code.length(); j++) {
                if (numberOfBits % 8 == 0 || numberOfBits == 0) {
                    byte[] newHuffmanCode = new byte[huffmanCode.length + 1];
                    for (int k = 0; k < huffmanCode.length; k++) {
                        newHuffmanCode[k] = huffmanCode[k];
                    }
                    huffmanCode = newHuffmanCode.clone();
                }
                char currentCode = code.charAt(j);
                if (currentCode == '0');
                else if (currentCode == '1') {
                    int filledBitsInLastByte = numberOfBits % 8;
                    switch (filledBitsInLastByte) {
                        case 0:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | ZERO);
                            break;
                        case 1:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | ONE);
                            break;
                        case 2:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | TWO);
                            break;
                        case 3:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | THREE);
                            break;
                        case 4:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | FOUR);
                            break;
                        case 5:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | FIVE);
                            break;
                        case 6:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | SIX);
                            break;
                        case 7:
                            huffmanCode[huffmanCode.length - 1] = (byte) (huffmanCode[huffmanCode.length - 1] | SEVEN);
                            break;
                    }
                }
                numberOfBits++;
            }
        }
        ArrayMap<String, Character> newDictionary = reverseDictionary(dictionary);
        zip = new Zip(newDictionary, huffmanCode, numberOfBits);
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(outputFileAddress + ".losezip"));
        writer.writeObject(zip);
        writer.close();
        FileOutputStream fos = new FileOutputStream(outputFileAddress + ".losehuff");
        fos.write(huffmanCode);
        fos.close();
    }

    private ArrayMap<Character, String> getDictionary() throws PriorityQueue.QueueOverflowedException, PriorityQueue.EmptyQueueException {
        Node treeRoot = getTree();
        ArrayMap<Character, String> dictionary = new ArrayMap<>();
        if (treeRoot.left == null && treeRoot.right == null) {
            dictionary.put(treeRoot.value.charAt(0), "0");
            return dictionary;
        }
        String rootCode = "";
        fillDictionary(treeRoot, rootCode, dictionary);
        return dictionary;
    }

    private void fillDictionary(Node node, String code, ArrayMap<Character, String> dictionary) {
        if (node != null) {
            Node right = node.right;
            Node left = node.left;
            if (right != null) {
                fillDictionary(right, code + "1", dictionary);
            }
            if (left != null) {
                fillDictionary(left, code + "0", dictionary);
            }
            if (right == null && left == null) {
                dictionary.put(node.value.charAt(0), code);
            }
        }
    }

    private Node getTree() throws PriorityQueue.QueueOverflowedException, PriorityQueue.EmptyQueueException {
        Node[] nodes = getNodes();
        PriorityQueue queue = new PriorityQueue(nodes.length);
        for (int i = 0; i < nodes.length; i++)
            queue.enQueue(nodes[i]);
        while (queue.getSize() != 1) {
            Node left = queue.deQueue();
            Node right = queue.deQueue();
            Node root = new Node(left.value + right.value, left.rep + right.rep);
            root.left = left;
            root.right = right;
            queue.enQueue(root);
        }
        return queue.deQueue();
    }

    private Node[] getNodes() {
        ArrayMap<Character, Integer> map = new ArrayMap<>();
        char[] parameterCharacters = parameter.toCharArray();
        for (int i = 0; i < parameterCharacters.length; i++) {
            char current = parameterCharacters[i];
            Integer lastValue = map.get(current);
            if (lastValue == null) map.put(current, 1);
            else map.put(current, lastValue + 1);
        }
        Node[] result = new Node[map.size()];
        Set<Character> set = map.keySet();
        Object[] keys = set.toArray();
        for (int i = 0; i < set.size(); i++) {
            result[i] = new Node(keys[i] + "", map.get(keys[i]));
        }
        return result;
    }

    private ArrayMap<String, Character> reverseDictionary(ArrayMap<Character, String> dictionary) {
        ArrayMap<String, Character> newDictionary = new ArrayMap<>();
        Set<Character> keySet = dictionary.keySet();
        Object[] keys = keySet.toArray();
        for (int i = 0; i < keys.length; i++) {
            char currentKey = (char) keys[i];
            newDictionary.put(dictionary.get(currentKey), currentKey);
        }
        return newDictionary;
    }

}
