/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * This Queue's First Priority is Node's repetition then it's string value
 * The Queue is Ascending
 * reza_mansouri@icloud.com
 */
package Collections;

public class PriorityQueue {
    private int size = 0, firstIndex = 0;
    private Node[] data;

    public PriorityQueue(int capacity) {
        data = new Node[capacity];
    }

    public void enQueue(Node element) throws QueueOverflowedException {
        if (isFull()) throw new QueueOverflowedException();
        int elementIndex = (firstIndex + size) % data.length;
        data[elementIndex] = element;
        size++;
        if (size != 1) placeLastElement(elementIndex);
    }

    public Node deQueue() throws EmptyQueueException {
        if (isEmpty()) throw new EmptyQueueException();
        size--;
        int resultIndex = firstIndex;
        firstIndex = (firstIndex + 1) % data.length;
        return data[resultIndex];
    }

    private void placeLastElement(int last) {
        for (int i = 0; i < size - 1; i++) {
            if (data[last].rep < data[reduceIndex(last)].rep) {
                Node temp = data[last];
                data[last] = data[reduceIndex(last)];
                data[reduceIndex(last)] = temp;
            } else if (data[last].rep == data[reduceIndex(last)].rep) {
                int valueComparisonAnswer = data[last].value.compareTo(data[reduceIndex(last)].value);
                if (valueComparisonAnswer == 0) break;
                else if (valueComparisonAnswer < 0) {
                    Node temp = data[last];
                    data[last] = data[reduceIndex(last)];
                    data[reduceIndex(last)] = temp;
                } else break;
            }
            last = reduceIndex(last);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == data.length;
    }

    public int getSize() {
        return size;
    }

    private int reduceIndex(int index) {
        if (index > 0) return index - 1;
        return data.length - 1;
    }

    public class EmptyQueueException extends Exception {
        EmptyQueueException() {
            super("Queue is Empty");
        }
    }

    public class QueueOverflowedException extends Exception {
        QueueOverflowedException() {
            super("Queue OverFlowed");
        }
    }
}
