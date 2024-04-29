package social;
import java.util.Arrays;
public class ResizableArrayBag<T> implements BagInterface<T> {
    private T[] bag;
    private static final int DEFAULT_CAPACITY= 25;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;
    private int numberOfEntries;
    /**
     * Creates an empty bag having a given capacity.
     * @param desiredCapacity The integer capacity desired.
     */
    public ResizableArrayBag(int desiredCapacity) {
        if (desiredCapacity <= MAX_CAPACITY) {
            // The cast is safe because the new array contains null entries
            checkCapacity(desiredCapacity);
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[]) new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            numberOfEntries = 0;
            integrityOK = true;
        } else {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
        }
    } // end constructor

    // Throws an exception if this object is not initialized.
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("ArrayBag object is corrupt.");
        }
    } // end checkIntegrity
    
    /** Throws an exception if the client requests a capacity that is too large. 
    @param capacity The desired capacity for the bag. */
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
        }
    } // end checkCapacity

    /** Creates an empty bag whose capacity is 25. */
    public ResizableArrayBag() {
        this(DEFAULT_CAPACITY);
    }   // end default constructor
    
    /** Gets the current number of entries in this bag.
    @return The integer number of entries currently in this bag. */
    public int getCurrentSize() {
        return numberOfEntries;
    }   // end getCurrentSize
    
    /** Sees whether this bag is empty.
    @return True if this bag is empty, or false if not. */
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }   // end isEmpty

    /** Sees whether this bag is full.
    @return True if the bag is full, or false if not. */
    public boolean isFull() {
        return numberOfEntries == MAX_CAPACITY;
    }   // end isFull
    
    /**
     * Adds a new entry to this bag.
     * @param newEntry The object to be added as a new entry.
     * @return True if the addition is successful, or false if not.
     */
    public boolean add(T newEntry) {
        checkIntegrity();
        if (isArrayFull()) {
            doubleCapacity();
        }
        boolean result = true;
        if (isArrayFull()) {
            result = false;
        } else {
            // Assertion: result is true here
            bag[numberOfEntries] = newEntry;
            numberOfEntries++;
        } // end if
        return result;
    } // end add
    
    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful, or null. */
    public T remove() {
        T result = removeEntry(numberOfEntries - 1);
        return result;
    }   // end remove
    
    /** Gets the current number of entries in this bag.
    @param anEntry The entry to be removed.
    @return The integer number of entries currently in this bag. */
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return anEntry.equals(result);
    }   // end remove
    
    /** Removes all entries from this bag. */
    public void clear() {               
        while (!isEmpty()) {
            remove();
        }
    }   // end clear
    
    /** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted. */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(bag[i])) {
                frequency++;
            }
        }
        return frequency;
    }   // end getFrequencyOf
    
    /** Tests whether this bag contains a given entry.
    @param anEntry The entry to find. */
    public boolean contains(T anEntry) {
        return getIndexOf(anEntry) > -1;
    }   // end contains
    
    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public T[] toArray() {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        
        for (int i = 0; i < numberOfEntries; i++) {
            result[i] = bag[i];
        }
        
        return result;
    }   // end toArray
    
    /** Retrieves all entries that are in this bag as an array of strings.
     * @param anArray The array to store the entries.
     * @return A newly allocated array of strings containing all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
        public String[] toArray(String[] anArray) {
            for (int i = 0; i < numberOfEntries; i++) {
                anArray[i] = (String) bag[i];
            }
        return anArray;
        }   // end toArray

    /** Retrieves the union of this bag and another bag.
    @param anotherBag The bag to compare to the contents of this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        BagInterface<T> unionBag = new ResizableArrayBag<>();
        
        for (T entry : bag) {
            if(entry!=null)
            unionBag.add(entry);
        }
        
        for (T entry : anotherBag.toArray()) {
            if(entry!=null)
            unionBag.add(entry);
        }
        
        return unionBag;
    }   // end union
    
    /** Retrieves the intersection of this bag and another bag.
    @param anotherBag The bag to compare to the contents of this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public ResizableArrayBag<T> intersection(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> intersectionBag = new ResizableArrayBag<>();
    
        for (T entry : bag) {
            if ((entry != null) && anotherBag.contains(entry)) {
                intersectionBag.add(entry);
                anotherBag.remove(entry);  // remove the item from the other bag
            }
        }
    
        return intersectionBag;
    }   // end intersection

    /** Retrieves the difference of this bag and another bag.
    @param anotherBag The bag to compare to the contents of this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public ResizableArrayBag<T> difference(BagInterface<T> anotherBag) {
        ResizableArrayBag<T> differenceBag = new ResizableArrayBag<>();
        for (T entry : bag) {
            if (entry != null) {
                int count1 = this.getFrequencyOf(entry);
                int count2 = anotherBag.getFrequencyOf(entry);
                int diffCount = count1 - count2;
    
                if (diffCount > 0) {
                    for (int i = 0; i < diffCount; i++) {
                        differenceBag.add(entry);
                    }
                }
            }
        }
    
        return differenceBag;
    }   // end difference
    
    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    private boolean isArrayFull() {
        return numberOfEntries >= bag.length;
    }
    
    /** Retrieves all entries that are in this bag.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    private void doubleCapacity() {
        int newCapacity = 2 * bag.length;
        bag = Arrays.copyOf(bag, newCapacity);
    }   // end doubleCapacity

    /** Retrieves all entries that are in this bag.
    @param anEntry The entry to find.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    private int getIndexOf(T anEntry) {
        int index = -1;
        for (int i = 0; i < numberOfEntries; i++) {
            if (anEntry.equals(bag[i])) {
                index = i;
                break;
            }
        }
        return index;
    }   // end getIndexOf
    
    /** 
    Removes and returns the entry at a given index within the array bag.
    If no such entry exists, returns null.
    Preconditions: 0 <= givenIndex < numberOfEntries;
    checkIntegrity has been called.
    @param givenIndex The index of the entry to be removed.
    @return The entry at the given index, or null if no such entry exists.
     */
    private T removeEntry(int givenIndex)
    {
        T result = null;
        if (!isEmpty() && (givenIndex >= 0))
        {
            result = bag[givenIndex]; // Entry to remove
            bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
            bag[numberOfEntries - 1] = null; // Remove last entry
            numberOfEntries--;
        } // end if
        return result;
    } // end removeEntry
} // end ResizableArrayBag
