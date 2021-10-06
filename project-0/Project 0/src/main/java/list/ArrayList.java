package list;

import java.util.Arrays;

public class ArrayList<E> implements ArrayListInterface<E> {
    private int size;
    private Object aList[];
    private static int def = 3;

    public ArrayList(){
        aList = new Object[def];
    }

    public void Enlarge() {
        //Enlarge method doubles the size of the list and copies the elements of the list into a list with the bigger size. Called if max length is reached.
        int nSize = aList.length * 2;
        aList = Arrays.copyOf(aList, nSize);
    }


    @Override
    public int size() {
        //Returns the size of the list
        return size;
    }

    @Override
    public void add(E e) {
        //If at max capacity, calls the enlarge method to increase the size of the list
        if(size >= aList.length){
            Enlarge();
        }
        //Adds the element to the end of the list
        aList[size++] = e;
    }

    @Override
    public void add(E e, int index) {
        //If at max capacity calls the enlarge method to increase the size of the list
        if(size >= aList.length){
            Enlarge();
        }
        //Adds the element at the specific index and shifts the remaining elements to the right
        for(int i = size; i >= index; i--){
            aList[i + 1] = aList[i];
        }
        //Insert the element at the index once elements have been shifted
        aList[index] = e;
        //increase size to account for new element
        size++;
    }

    @Override
    public E get(int index) {
        //Returns the element at a given index
        return (E) aList[index];
    }

    @Override
    public void remove(int index) {
        //shifts over all the elements in the list, and replaces the element at the index given
        for(int i = index; i < size; i++){
            aList[i] = aList[i + 1];
        }
        size--;
    }

    @Override
    public void clear() {
        //creates a new array, maintaining the size of the original
        Object newAList[] = new Object[size];
        //clears the current array of any elements by setting it equal to a copy of the newAList
        aList = Arrays.copyOf(newAList, size);
        size = 0;

    }

    @Override
    public boolean contains(E e) {
        //iterate through a loop and check each index to see if the element exists inside
        for(int i = 0; i < size; i++){
            if(aList[i] == e){
                //if element is found, returns true confirming it is contained in the list
                return true;
            }
        }
        //if element is not found, returns false
        return false;
    }

}
