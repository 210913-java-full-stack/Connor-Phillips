package list;

public interface ArrayListInterface<E> {

    //Length of the ArrayList
    int size();

    //Add an element to the end of the ArrayList
    void add(E e);

    //Add an element at a specific index, shifts the other elements accordingly
    void add(E e, int index);

    //Find and return an element at an index
    E get(int index);

    //Remove an element at an index, shifts the other elements accordingly
    void remove(int index);

    //clear the ArrayList
    void clear();

    //check to see if element exists in the ArrayList, returns a boolean and index if it does exist
    boolean contains(E e);
}
