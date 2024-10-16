package projecttwo;

import java.util.Iterator;

/**
 * {@code @author:} J-JHsu
 * A generic list implementation that holds elements and allows for iteration.
 * @param <E> the type of elements in this list
 */

public class List<E> implements Iterable<E> {
    private static final int INITIAL_CAPACITY = 4;
    private static final int CAPACITY_INCREMENT = 4;
    private E[] elements; // Generic array to hold elements
    private int size; // Number of elements in the list

    // Constructor
    /**
     * Default constructor initializing the list with an initial capacity.
     */
    @SuppressWarnings("unchecked")
    public List() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Add an element to the list.
     * @param e the element to be added
     */
    public void add(E e) {
        if (contains(e)) {
            return;
        }
        if (size >= elements.length) {
            grow();
        }
        elements[size++] = e;
    }

    /**
     * Remove an element from the list.
     * @param e the element to be removed
     */
    public void remove(E e) {
        int index = find(e);
        if (index == -1) {
            return;
        }
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Clear last element
    }

    /**
     * Check if the list contains a given element.
     * @param e the element to check for
     * @return true if the list contains the element, false otherwise
     */
    public boolean contains(E e) {
        return find(e) != -1;
    }

    /**
     * Private helper method to find the index of an element.
     * @param e the element to find
     * @return the index of the element if found, -1 otherwise
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Private method to grow the capacity of the list.
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newElements = (E[]) new Object[elements.length + CAPACITY_INCREMENT];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    /**
     * Get an element by its index.
     * @param index the index of the element to get
     * @return the element at the specified index, or null if out of bounds
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[index];
    }

    /**
     * Replace the element at a specific index.
     * @param index the index of the element to replace
     * @param e the new element to set
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        elements[index] = e;
    }

    /**
     * Get the current size of the list.
     * @return the number of elements in the list
     */
    public int size() {
        return size;
    }

    /**
     * Check if the list is empty.
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Return an iterator over elements of type {@code E}.
     * @return an Iterator.
     */
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    // Inner class to implement iterator
    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return elements[currentIndex++];
        }
    }
}
