/*
 * Developed by Reza Mansouri 2020
 * For Data Structures Final Project in Bachelor
 * Third Term of Software Engineering at Kharazmi University of Tehran
 * std_reza_mansouri@khu.ac.ir
 */
package Collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class ArraySet<E> implements Set<E>, Serializable {

    private Element[] elements;
    private int size;

    private static class Element<E> {
        E value;
    }

    public ArraySet() {
        elements = new Element[1000];
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (!isEmpty()) {
            for (int i = 0; i < size; i++) {
                if (o.equals(elements[i].value))
                    return true;
            }
        }
        return false;
    }

    @Override
    public void clear() {
        elements = new Element[1000];
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        for (int i = 0; i < size; i++) {
            result[i] = elements[i].value;
        }
        return result;
    }

    @Override
    public boolean add(E e) {
        if (!contains(e)) {
            if (size == elements.length) {
                expand();
            }
            Element<E> element = new Element<>();
            element.value = e;
            int index = (isEmpty()) ? 0 : size;
            elements[index] = element;
            size++;
            return false;
        }
        return true;
    }

    private void expand() {
        Element[] newElements = new Element[elements.length * 2];
        for (int i = 0; i < elements.length; i++) newElements[i] = elements[i];
        elements = newElements;
    }

    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------

    @Override
    public <T> T[] toArray(T[] a) {
        /*
        DOES NOT WORK
         */
        return null;
    }

    @Override
    public Iterator<E> iterator() {
        /*
          DOES NOT WORK
         */
        return null;
    }

    @Override
    public boolean remove(Object o) {
        /*
          DOES NOT WORK
         */
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        /*
         * DOES NOT WORK
         */
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        /*
         * DOES NOT WORK
         */
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        /*
         * DOES NOT WORK
         */
        return false;
    }

    @Override

    public boolean removeAll(Collection<?> c) {
        /*
         * DOES NOT WORK
         */
        return false;
    }
}
