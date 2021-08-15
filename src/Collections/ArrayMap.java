package Collections;

//Author : Reza Mansouri Student at Computer Engineering at Kharazmi University
//Date : January 16th 2019

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class ArrayMap<K extends Comparable, V> implements Map<K, V>, Serializable {
    private Element[] elements;
    private int filledIndex = 0, elementsSize = 100;
    private Set arraySet = new ArraySet();
//----------------------------------------------

    public ArrayMap() {
        elements = new Element[elementsSize];
    }
//----------------------------------------------

    static class Element<K, V> implements Serializable {
        K key;
        V value;

        Element(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public String toString() {
            return key + "=" + value;
        }
    }
//----------------------------------------------

    public int size() {
        return filledIndex;
    }
//----------------------------------------------

    public boolean isEmpty() {
        return (filledIndex == 0);
    }
//----------------------------------------------

    public boolean containsKey(Object key) {
        for (int i = 0; i < size(); i++)
            if (elements[i].key.equals(key)) return true;
        return false;
    }
//----------------------------------------------

    public boolean containsValue(Object value) {
        for (int i = 0; i < size(); i++)
            if (elements[i].value != null)
                if (elements[i].value.equals(value)) return true;
        return false;
    }


//----------------------------------------------

    public V get(Object key) {
        K myKey = (K) key;
        V result = null;
        for (int i = 0; i < size(); i++) {
            if (myKey.compareTo(elements[i].key) == 0) {
                result = (V) elements[i].value;
                break;
            }
        }
        return result;
    }
//----------------------------------------------

    public V put(K key, V value) {
        if (filledIndex == elementsSize - 1) expand();
        V result;
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (key.compareTo(elements[i].key) == 0) {
                    result = (V) elements[i].value;
                    elements[i].value = value;
                    return result;
                }
            }
        }
        elements[filledIndex] = new Element(key, value);
        filledIndex++;
        return null;
    }
//----------------------------------------------

    public V remove(Object key) {
        V result = null;
        if (!isEmpty()) {
            for (int i = 0; i < size(); i++) {
                if (elements[i].key.equals(key)) {
                    result = (V) elements[i].value;
                    elements[i] = null;
                    filledIndex--;
                    break;
                }
            }
        }
        shift();
        return result;
    }
//----------------------------------------------

    public void putAll(Map m) {
        Set keys = m.keySet();
        Collection values = m.values();
        Object[] _keys = keys.toArray();
        Object[] _values = values.toArray();
        for (int i = 0; i < m.size(); i++) {
            elements[i] = new Element(_keys[i], _values[i]);
        }
        filledIndex = m.size();
    }
//----------------------------------------------

    public void clear() {
        for (int i = 0; i < size(); i++) {
            elements[i] = null;
            filledIndex = 0;
        }
    }
//----------------------------------------------

    public Set<K> keySet() {
        arraySet.clear();
        for (int i = 0; i < size(); i++) {
            arraySet.add(elements[i].key);
        }
        return arraySet;
    }
//----------------------------------------------

    public Collection<V> values() {
        arraySet.clear();
        for (int i = 0; i < size(); i++) {
            arraySet.add(elements[i].value);
        }
        return arraySet;
    }
//----------------------------------------------

    public Set<Entry<K, V>> entrySet() {
        arraySet.clear();
        for (int i = 0; i < size(); i++) {
            arraySet.add(elements[i]);
        }
        return arraySet;
    }
//----------------------------------------------

    public String toString() {
        String result = "{";
        for (int i = 0; i < size(); i++) {
            result += elements[i];
            if (i != size() - 1) result += ", ";
        }
        result += "}";
        return result;
    }

    //********************************************
    private void shift() {
        for (int i = 0; i < filledIndex; i++) {
            if (elements[i] == null) {
                for (int x = i; x < filledIndex; x++) {
                    elements[x] = elements[x + 1];
                }
            }
        }
    }

    private void expand() {
        elementsSize *= 1.2;
        Element[] newElements = new Element[elementsSize];
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }
    //********************************************
}
//----------------------------------------------
