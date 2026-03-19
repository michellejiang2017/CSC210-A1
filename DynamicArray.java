/**
 * A Dynamic Array is like a list that is backed by an array. 
 * It allows adding, removing, and accessing elements in a way similar to an ArrayList.
 *
 * @param <T> The generic class T can hold any object type such as Integer, String, etc. 
 * @author Michelle Jiang
 */
public class DynamicArray<T> implements ListADT<T> {
    /**
     * The underlying array that holds the elements of the DynamicArray.
     */
    T[] data; 
    /**
     * The number of elements in the DynamicArray.
     */
    int size; 

    /**
     * Initializes a DynamicArray with the given initial capacity.
     * @param capacity the initial capacity of the array
     */
    public DynamicArray(int capacity) {
        this.data = this.makeArray(capacity); 
        this.size = 0; 
    }

    /**
     * Creates a copy of the given DynamicArray.
     * This is a deep copy where all elements are copied into a new array.
     * @param sourceArray the DynamicArray to copy
     */
    public DynamicArray(DynamicArray<T> sourceArray) {
        this.data = this.makeArray(sourceArray.data.length);
        for (int i = 0; i < sourceArray.size; i++) {
            this.data[i] = sourceArray.data[i];
        }
        this.size = sourceArray.size; 
    }
    /**
     * Sets the value at the given index
     * @param index the index whose value is changed
     * @param value the value to change the index to 
     * @return the item that was previously at this position
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the list
     */
    public T set(int index, T value) {
        checkIndex(index);
        T returnValue = this.data[index];
        this.data[index] = value; 
        return returnValue;
    }

    /**
     * Adds element to existing list. Note: If the element type isn't the same as the list type, the code will not compile. 
     * @param index of where the new element should be added
     * @param value the element to be added to the ListADT object
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the list
     */
    public void add(int index, T value) {
        if (index < 0 || index > this.size()) { 
            throw new IndexOutOfBoundsException();
        } else {
            if (size == this.data.length) {
                T[] dataCopy = this.makeArray(size * 2 + 1);
                for (int i = 0; i < size; i++) {
                    dataCopy[i] = this.data[i];
                }
                data = dataCopy;
            }
            for (int i = this.size() - 1; i >= index; i--) {
                this.data[i + 1] = this.data[i];
            }
            this.data[index] = value;
            size += 1; 
        }
    }

    /**
     * Adds element at end of list. 
     * @param value the element to be added to the ListADT object
     */
    public void add(T value) { 
        this.add(this.size(), value);
    }

    /**
     * Queries number of elements in list. If empty, returns zero. 
     * @return size of list. 
     */
    public int size() {
        return this.size; 
    }

    /**
     * Tests if a list is empty or not. 
     * @return true if empty, false if has elements
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Converts the DynamicArray to a string.
     * @return DynamicArray in String type
     */
    public String toString() {
        String returnString = "["; 
        for (int i=0; i<this.size(); i++) {
            returnString += String.valueOf(this.data[i]);
            if (i < this.size() - 1) {
                returnString += ", ";
            }
        }
        returnString += "]";
        return returnString;
    }

    /**
     * Accesses element at a given index. 
     * @param index the index to query
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the list
     * @return value at specified index
     */
    public T get(int index) {
        checkIndex(index);
        return this.data[index];
    }

    /**
     * Removes item from list at given index. 
     * @param index the index to query
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the list
     * @return the element at the index specified
     */
    public T remove(int index) {
        checkIndex(index);
        T returnValue = this.data[index];
        this.data[index] = null;
        for (int i = index + 1; i < size; i++) {
            this.data[i - 1] = this.data[i];
        }
        size -= 1; 
        return returnValue;
    }

    /**
     * Appends the elements of the given array to the end of this array, and returns a new DynamicArray containing the result.
     * @param addArray the array to be appended to this array
     * @return a new DynamicArray containing the elements of this array followed by the elements of addArray
     */
    public DynamicArray<T> append(DynamicArray<T> addArray) {
        DynamicArray<T> newArray = new DynamicArray<T>(addArray.size + this.size());
        for (int i = 0; i < this.size(); i++) {
            newArray.data[i] = this.data[i];
        }
        for (int i = 0; i < addArray.size; i++) {
            newArray.data[this.size() + i] = addArray.data[i];
        }
        newArray.size = this.size + addArray.size;
        return newArray;
    }

    /**
     * Adds the elements of the given array to the index of this array, and returns a new DynamicArray containing the result.
     * @param index the index at which to insert the elements of addArray
     * @param addArray the array to be added to the end of this array
     * @return a new DynamicArray containing the elements of this array with the elements of addArray inserted at the specified index
     */
    public DynamicArray<T> addAll(int index, DynamicArray<T> addArray) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        DynamicArray<T> newArray = new DynamicArray<T>(addArray.size + this.size());
 
        for (int i = 0; i < index; i++) {
            newArray.data[i] = this.data[i];
        }
    
        for (int i = 0; i < addArray.size; i++) {
            newArray.data[i + index] = addArray.data[i];
        }
        
        for (int i = index; i < this.size(); i++) {
            newArray.data[i + addArray.size] = this.data[i];
        }

        newArray.size = this.size + addArray.size;
        return newArray;
    }

    /**
     * Checks if the given index is valid for accessing or modifying the list. If the index is out of bounds, an IndexOutOfBoundsException is thrown.
     * @param index the index to check
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * Removes elements from startIndex to endIndex (exclusive) and returns a new DynamicArray with the remaining elements.
     * @param fromIndex the index at which to start removing (inclusive)
     * @param toIndex the index at which to stop removing (exclusive)
     * @return a new DynamicArray containing elements outside the removed range
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public DynamicArray<T> delete(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        DynamicArray<T> newArray = new DynamicArray<T>(this.size() - (toIndex - fromIndex));
        
        for (int i = 0; i < fromIndex; i++) {
            newArray.data[i] = this.data[i];
        }
        
        for (int i = toIndex; i < this.size(); i++) {
            newArray.data[i - (toIndex - fromIndex)] = this.data[i];
        }
        
        newArray.size = this.size() - (toIndex - fromIndex);
        return newArray;
    }

    /**
     * Returns a new DynamicArray containing the elements from one index up to just before another, [fromIndex, toIndex).
     * @param fromIndex the starting index (inclusive)
     * @param toIndex the ending index (exclusive)
     * @return a new DynamicArray containing elements from fromIndex to toIndex
     * @throws IndexOutOfBoundsException if indices are invalid or fromIndex > toIndex
     */
    public DynamicArray<T> extract(int fromIndex, int toIndex) {
        if (fromIndex < 0 || toIndex > this.size() || fromIndex > toIndex) {
            throw new IndexOutOfBoundsException();
        }
        DynamicArray<T> newArray = new DynamicArray<T>(toIndex - fromIndex);
        
        for (int i = fromIndex; i < toIndex; i++) {
            newArray.data[i - fromIndex] = this.data[i];
        }
        
        newArray.size = toIndex - fromIndex;
        return newArray;
    }

    /**
     * Returns the elements from the specified index and after as a new DynamicArray.
     * @param index the starting index (inclusive)
     * @return a new DynamicArray containing elements from index to the end
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public DynamicArray<T> splitSuffix(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.extract(index, this.size());
    }

    /**
     * Returns the elements before the specified index as a new DynamicArray.
     * @param index the ending index (exclusive)
     * @return a new DynamicArray containing elements from the beginning to before index
     * @throws IndexOutOfBoundsException if index is invalid
     */
    public DynamicArray<T> splitPrefix(int index) {
        if (index < 0 || index > this.size()) {
            throw new IndexOutOfBoundsException();
        }
        return this.extract(0, index);
    }

      /**
     * Returns a subarray from startIndex to endIndex (exclusive) as a new DynamicArray.
     * @param startIndex the starting index (inclusive)
     * @param endIndex the ending index (exclusive)
     * @return a new DynamicArray containing elements from startIndex to endIndex
     * @throws IndexOutOfBoundsException if indices are invalid
     */
    public DynamicArray<T> sublist(int startIndex, int endIndex) {
        if (startIndex < 0 || endIndex > this.size() || startIndex > endIndex) {
            throw new IndexOutOfBoundsException();
        }
        DynamicArray<T> newArray = new DynamicArray<T>(endIndex - startIndex);
        
        for (int i = startIndex; i < endIndex; i++) {
            newArray.data[i - startIndex] = this.data[i];
        }
        
        newArray.size = endIndex - startIndex;
        return newArray;
    }


    /**
     * Creates a new generic array of the given capacity.
     * <p>
     * Java does not allow direct creation of generic arrays. This helper method
     * safely encapsulates the required cast and suppresses the expected unchecked
     * cast warning.
     *
     * @param capacity the desired length of the array
     * @return a new array of type T[] with the given capacity
     */    
    @SuppressWarnings("unchecked")
    private T[] makeArray(int capacity) {
        return (T[]) new Object[capacity];
    }
}
