import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DynamicArrayTest {

    private DynamicArray<Character> a1;
    private DynamicArray<Character> a2;
    private DynamicArray<Character> empty;
    private DynamicArray<Character> s;

    /**
     * Initializes DynamicArray<Character> instances to be used for testing.
     * Re-initializes before each test.
     * This ensures that tests do not interfere with one another.
     */
    @Before
    public void setUp() {
        a1 = stringToArray("abcdef");
        a2 = stringToArray("wxyz");
        empty = stringToArray("");
        s = stringToArray("s");
    }

    /**
     * Puts the characters of a string into an array structure
     */
    public DynamicArray<Character> stringToArray(String s) {
        DynamicArray<Character> result = new DynamicArray<Character>(s.length());
        for (int i = 0; i < s.length(); i++) {
            result.add(i, s.charAt(i));
        }
        return result;
    }

    /**
     * Compares the sizes of a DynamicArray<Character> and a string
     */
    public void compareSize(DynamicArray<Character> arr, String s){
        assertEquals("["+s+"] Array lengths are equal", arr.size(), s.length());
    }

    /**
     * Compares each element in a DynamicArray<Character>
     * against those in a string.
     */
    public void compareToString(DynamicArray<Character> arr, String s) {
        for (int i = 0; i < arr.size(); i++) {
            assertEquals("["+s+"] Elements are equal at index " + i, arr.get(i).charValue(), s.charAt(i));
        }
    }

    /**
     * Tests that the constructor creates a DynamicArray of the correct size.
      * Also tests that the size of the array is correct after adding elements.
     */
    @Test
    public void testConstructor() {
        compareSize(a1, "abcdef");
        compareSize(a2, "wxyz");
        compareSize(empty, "");
        compareSize(s, "s");
    }

    /**
     * Tests adding elements at specific indices in the DynamicArray.
     */
    @Test
    public void testAdd() {
        a1.add(3,'Q');
        compareToString(a1, "abcQdef");
        a2.add(0,'R');
        compareToString(a2, "Rwxyz");
        s.add(1,'T');
        compareToString(s, "sT");
        empty.add(0,'U');
        compareToString(empty, "U");
    }

    @Test
    public void testAddInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            a1.add(-1, 'X');
    });

    assertThrows(IndexOutOfBoundsException.class, () -> {
        a1.add(a1.size() + 1, 'Y');
    });
}


    /**
     * Tests adding elements at the end of the DynamicArray.
     */
    @Test
    public void testAddEnd() {
        a1.add('Q');
        compareToString(a1, "abcdefQ");
        a2.add('R');
        compareToString(a2, "wxyzR");
        s.add('T');
        compareToString(s, "sT");
        empty.add('U');
        compareToString(empty, "U");
    }

    /**
     * Tests that the get method returns the correct element at a given index.
     */
    @Test
    public void testGet() {
        assertEquals("a1: get(0) is 'a'", a1.get(0).charValue(), 'a');
        assertEquals("a1: get(3) is 'd'", a1.get(3).charValue(), 'd');
        assertEquals("a2: get(0) is 'w'", a2.get(0).charValue(), 'w');
        assertEquals("a2: get(2) is 'y'", a2.get(2).charValue(), 'y');
        assertEquals("s: get(0) is 's'", s.get(0).charValue(), 's');
    }

    /**
     * Tests that the get method throws an IndexOutOfBoundsException when given an invalid index.
     */
    @Test
    public void testGetInvalidIndex() {
        try {
            a1.get(-1);
            fail("a1: get(-1) should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            a1.get(6);
            fail("a1: get(6) should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /**
     * Tests that the toString method returns a string representation of the DynamicArray in the correct format.
     */
    @Test
    public void testToString() {
        assertEquals("a1: toString() is '[a, b, c, d, e, f]'", a1.toString(), "[a, b, c, d, e, f]");
        assertEquals("a2: toString() is '[w, x, y, z]'", a2.toString(), "[w, x, y, z]");
        assertEquals("s: toString() is '[s]'", s.toString(), "[s]");
        assertEquals("empty: toString() is '[]'", empty.toString(), "[]");
    }

    /**
     * Tests that the size method returns the correct number of elements in the DynamicArray.
      * Also tests that the size is updated correctly after adding elements.
     */
    @Test
    public void testSize() {
        assertEquals("a1: size() is 6", a1.size(), 6);
        assertEquals("a2: size() is 4", a2.size(), 4);
        assertEquals("s: size() is 1", s.size(), 1);
        assertEquals("empty: size() is 0", empty.size(), 0);
    }

    /**
     * Tests that the isEmpty method returns true for an empty DynamicArray and false for a non-empty DynamicArray.
      * Also tests that isEmpty returns false after adding elements to an empty DynamicArray.
     */
    @Test 
    public void testIsEmpty() {
        assertFalse("a1: isEmpty() is false", a1.isEmpty());
        assertFalse("a2: isEmpty() is false", a2.isEmpty());
        assertFalse("s: isEmpty() is false", s.isEmpty());
        assertTrue("empty: isEmpty() is true", empty.isEmpty());
    }

    /**
     * Tests that the set method replaces the element at the specified index and returns the old value.
      * Also tests that the set method throws an IndexOutOfBoundsException when given an invalid index.
      * Tests that the set method updates the string representation of the DynamicArray correctly.
     */
    @Test
    public void testSet() {
        assertEquals("a1: set(0, 'Q') returns 'a'", a1.set(0, 'Q').charValue(), 'a');
        compareToString(a1, "Qbcdef");
        assertEquals("a2: set(2, 'R') returns 'y'", a2.set(2, 'R').charValue(), 'y');
        compareToString(a2, "wxRz");
        assertEquals("s: set(0, 'T') returns 's'", s.set(0, 'T').charValue(), 's');
        compareToString(s, "T");
    }

    /**
     * Tests that the set method throws an IndexOutOfBoundsException when given an invalid index.
      * Also tests that the set method does not modify the DynamicArray when given an invalid index.
     */
    @Test
    public void testSetInvalidIndex() {
        try {
            a1.set(-1, 'Q');
            fail("a1: set(-1, 'Q') should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            a1.set(6, 'Q');
            fail("a1: set(6, 'Q') should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /**
     * Tests that the remove method removes the element at the specified index and returns it.
      * Also tests that the remove method throws an IndexOutOfBoundsException when given an invalid index.
      * Tests that the remove method updates the string representation of the DynamicArray correctly.
     */
    @Test
    public void testRemove() {
        assertEquals("a1: remove(0) returns 'a'", a1.remove(0).charValue(), 'a');
        compareToString(a1, "bcdef");
        assertEquals("a2: remove(2) returns 'y'", a2.remove(2).charValue(), 'y');
        compareToString(a2, "wxz");
        assertEquals("s: remove(0) returns 's'", s.remove(0).charValue(), 's');
        compareToString(s, "");
    }

    /**
     * Tests that the remove method throws an IndexOutOfBoundsException when given an invalid index.
      * Also tests that the remove method does not modify the DynamicArray when given an invalid index.
     */
    @Test
    public void testRemoveInvalidIndex() {
        try {
            a1.remove(-1);
            fail("a1: remove(-1) should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            a1.remove(6);
            fail("a1: remove(6) should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
        try {
            empty.remove(0);
            fail("empty: remove(0) should throw IndexOutOfBoundsException");
        } catch (IndexOutOfBoundsException e) {
            // expected
        }
    }

    /**
     * Tests that the DynamicArray resizes correctly when adding elements beyond its initial capacity.
     */
    @Test
    public void testResize() {
        DynamicArray<Integer> arr = new DynamicArray<>(2);
        arr.add(1);
        arr.add(2);
        arr.add(3); // triggers resize
        assertEquals(3, arr.size());
        assertEquals(Integer.valueOf(1), arr.get(0));
        assertEquals(Integer.valueOf(2), arr.get(1));
        assertEquals(Integer.valueOf(3), arr.get(2));
    }


    // ~*~*~*~*~ Append Tests Below ~*~*~*~*~

    /**
     * Tests that appending two non-empty arrays results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAppendStandard() {
        compareToString(a1.append(a2), "abcdefwxyz");
        compareToString(a2.append(a1), "wxyzabcdef");
    }

    /**
     * Tests that appending a non-empty array to itself results in
     * a new array containing the elements repeated twice.
     */
    @Test
    public void testAppendSelf() {
        compareToString(a1.append(a1), "abcdefabcdef");
        compareToString(a2.append(a2), "wxyzwxyz");
    }

    /**
     * Tests that appending a non-empty array and an array of
     * length one results in a new array containing the elements
     * of both, in order.
     */
    @Test
    public void testAppendSingle() {
    compareToString(a1.append(s),"abcdefs");
    compareToString(s.append(a1),"sabcdef");
    compareToString(s.append(s),"ss");
    }

    /**
     * Tests that appending an empty array
     * results in a new array that matches the other array
     */
    @Test
    public void testAppendEmpty() {
        compareToString(a1.append(empty), "abcdef");
        compareToString(empty.append(a1), "abcdef");
        compareToString(empty.append(empty), "");
    }

    /**
     * Tests that adding all elements of one array to another at a specific index results in
     * a new array containing the elements of both, in order.
     */
    @Test
    public void testAddAll() {
        compareToString(a1.addAll(3, a2), "abcwxyzdef");
        compareToString(a1.addAll(0, a2), "wxyzabcdef");
        compareToString(a1.addAll(a1.size(), a2), "abcdefwxyz");
    }

    /**
     * Tests that adding all elements of one array to another at an invalid index throws an IndexOutOfBoundsException.
     */
    @Test
    public void testAddAllInvalidIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> {
            a1.addAll(-1, a2);
        });
        assertThrows(IndexOutOfBoundsException.class, () -> {
        a1.addAll(a1.size() + 1, a2);
        });
    }
}




