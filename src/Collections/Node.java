/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * reza_mansouri@icloud.com
 */
package Collections;

public class Node {
    public String value;
    public int rep;
    public Node right, left;

    public Node(String v, int r) {
        value = v;
        rep = r;
    }

    @Override
    public String toString() {
        return "rep: " + rep + " value: " + value;
    }
}
