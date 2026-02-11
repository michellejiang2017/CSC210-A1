/**
 * TODO: Describe what this class represents and how it works at a high level
 *
 * @param <T> TODO: Describe what type of elements this class can store
 * @author Michelle Jiang
 */
public class DynamicArray<T> {
    T[] data; 
    int size; 

    public DynamicArray(int capacity) {
        this.data = this.makeArray(capacity); 
        this.size = 0; 
    }

    public DynamicArray(DynamicArray<T> sourceArray) {
        this.data = this.makeArray(sourceArray.data.length);
        for (int i=0; i<sourceArray.size; i++) {
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
        if (index < 0 || index >= this.size()) {
            throw new IndexOutOfBoundsException();
        } else {
            T returnValue = this.data[index];
            this.data[index] = value; 
            return returnValue;
        }
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
                T[] dataCopy = this.makeArray(size*2);
                for (int i=0;i<size;i++) {
                    dataCopy[i] = this.data[i];
                }
                data = dataCopy;
            }
            for (int i=this.size()-1; i>=index; i--) {
                this.data[i+1] = this.data[i];
            }
            this.data[index] = value;
            size += 1; 
        }
    }

    /**
     * Adds element at end of list. 
     * @param value the element to be added to the ListADT object
     */
    public void append(T value) { 
        this.add(size, value);
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
            if (i<this.size-1) {
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
        if (index < 0 || index > this.size()) { 
            throw new IndexOutOfBoundsException();
        else { 
            return this.data[index];
        }
    }

    /**
     * Removes item from list at given index. 
     * @param index the index to query
     * @throws IndexOutOfBoundsException if the index is less than 0 or greater than or equal to the size of the list
     * @return the element at the index specified
     */
    public T remove(int index) {
        if (index < 0 || index >= this.size()) { 
            throw new IndexOutOfBoundsException();
        } else {
            T returnValue = this.data[index];
            this.data[index] = null;
            for (int i=index+1; i<size; i++) {
                this.data[i-1] = this.data[i];
            }
            size -= 1; 
            return returnValue;
        }
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