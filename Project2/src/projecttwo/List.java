package projecttwo;

import java.util.Iterator;

//A generic List class to manage collections of objects like Appointments or Providers.\
public class List<E> implements Iterable<E> {
    private static final int INITIAL_CAPACITY = 4;
    private static final int CAPACITY_INCREMENT = 4;
    private E[] elements; // Generic array to hold Appointments or Providers
    private int size; // Number of elements in the list

    // Constructor
    @SuppressWarnings("unchecked")
    public List() {
        elements = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    // Add method to insert elements into the list
    public void add(E e) {
        if (contains(e)) {
            return;
        }
        if (size >= elements.length) {
            grow();
        }

        elements[size++] = e;
    }

    // Remove method to delete elements from the list
    public void remove(E e) {
        int index = find(e);
        if (index == -1) {
            return;
        }

        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null; // Clear last element
    }

    // Method to check if the list contains a given element
    public boolean contains(E e) {
        return find(e) != -1;
    }

    // Private helper method to find the index of an element
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    // Private method to grow the capacity of the list
    @SuppressWarnings("unchecked")
    private void grow() {
        E[] newElements = (E[]) new Object[elements.length + CAPACITY_INCREMENT];
        System.arraycopy(elements, 0, newElements, 0, size);
        elements = newElements;
    }

    // Get method to retrieve elements by index
    public E get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return elements[index];
    }

    // Return the current size of the list
    public int size() {
        return size;
    }

    // Return true if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }
    
    public Iterator<E> iterator() {
        return new ListIterator();
        //return null;
    }
    

    
    // Inner class to implement iterator
    private class ListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override public boolean hasNext() {
            return currentIndex < size;
        }

        @Override public E next() {
            return elements[currentIndex++];
        }
    }
    
    
    /* doesn't matter I guess, similar function can be achieved in ClinicManager.class
    // Rotate a circular list of technicians for assigning imaging appointments.
    public E getNextTechnician() {
        E technician = elements[0]; // Circular rotation logic here
        // Move the first technician to the end of the list
        remove(technician);
        add(technician);
        return technician;
    }
    */
    /*
    public static void main(String [] args) {

        List<Integer> testINT = new List<Integer>();

        Random rand = new Random();

        for(int i = 0; i < 99; i += 1){
            testINT.add(rand.nextInt(1000));
        }

        System.out.println(testINT.get(1));
    }
    */
}
