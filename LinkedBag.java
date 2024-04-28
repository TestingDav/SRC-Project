package social;

public class LinkedBag<T> implements BagInterface<T> {
    private Node firstNode; // reference to first node
    private int numberOfEntries;
    private class Node{ // private inner class
        private T data; //entry in bag
        private Node nextNode; //link to next node
        private Node(T dataPortion){
            this(dataPortion, null);
        }   //end constructor

        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            this.nextNode = nextNode;
        }   //end constructor

        /*Returns the number of entries in this bag.
        @return The integer number of entries currently in the bag.*/
        private Node getNextNode(){
            return nextNode;
        }   //end getNextNode
        
        //Sets the next node
        private void setNextNode(Node next){
            nextNode = next;
        }   //end setNextNode
        
        //@return The data at the node
        private T getData(){
            return data;
        }   //end getData

        private void setData(T newData){
            data = newData;
        }   //end setData

    }   //end Node Class

    /** Gets the number of entries currently in this bag.
    @return The integer number of entries currently in the bag.*/   
    public int getCurrentSize() {
        return numberOfEntries;
    }   //end getCurrentSize

    /** Sees whether this bag is empty.
    @return True if the bag is empty, or false if not.*/
    public boolean isEmpty() {
        return numberOfEntries == 0;
    }   //end isEmpty

    /** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry
    @return True if the addition is successful, or false if not.
    */
    public boolean add(T newEntry) {
        // Add to beginning of chain:
        Node newNode = new Node(newEntry);
        newNode.setNextNode(firstNode); //
        firstNode = newNode; // New node is at beginning of chain
        numberOfEntries++;
        return true;
    }   //end add
    

    /** Removes one unspecified entry from this bag, if possible.
    @return Either the removed entry, if the removal was successful, or null. */
        public T remove(){
        T result = null;
        if (firstNode != null){
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // Remove first node from chain
            numberOfEntries--;
        } // end if
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false otherwise. */
    public boolean remove(T anEntry){
        boolean result = false;
        Node nodeN = getReferenceTo(anEntry);
        if (nodeN != null){
            // Replace located entry with entry in first node
            nodeN.setData(firstNode.getData());
            // Remove first node
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            result = true;
        } // end if
        return result;
    } // end remove

    /**
     * Locates a given entry within this bag.
     * Returns a reference to the node containing the entry, if located, or null otherwise.
     * @param anEntry
     * @return Node containing the entry, if located, or null otherwise.
     */
    private Node getReferenceTo(T anEntry){
        boolean found = false;
        Node currentNode = firstNode;
        while (!found && (currentNode != null))
        {
            if
            (anEntry.equals(currentNode.getData()))
              found = true;
            else
               currentNode = currentNode.getNextNode();
        } // end while
        return currentNode;
    } // end getReferenceTo

    /** Removes all entries from this bag. */
    public void clear() {
        while(!isEmpty()){
            remove();
        }   //end while
    }   //end clear

    /**
     * Counts the number of times a given entry appears in this bag.
     * @param anEntry The entry to be counted.
     * @return The number of times anEntry appears in the bag.
     */
    public int getFrequencyOf(T anEntry) {
        int frequency = 0;
        int counter = 0;
        Node currentNode = firstNode;
        while((counter < numberOfEntries) && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                frequency++;
            }   //end if
            counter++;
            currentNode = currentNode.getNextNode();
        }   //end while
        return frequency;
    }

    /**
     * Retrieves all entries that are in this bag.
     * Note: If the bag is empty, the returned array is empty.
     * @param anEntry The entry to be counted.
     * @return A newly allocated array of all the entries in the bag.
     */
    public boolean contains(T anEntry) {
       boolean found = false;
        Node currentNode = firstNode;
        while(!found && (currentNode != null)){
            if(anEntry.equals(currentNode.getData())){
                found = true;
            } else {
                currentNode = currentNode.getNextNode();
            }   //end while
        }
        return found;
    }   //end contains

    /** Retrieves all entries that are in this bag.
     * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public T[] toArray() {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries]; // Unchecked cast
        int index = 0;
        Node currentNode = firstNode;
        while((index < numberOfEntries) && (currentNode != null)){
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }   //end while
        return result;
    }   //end toArray

    /** Retrieves the union of this bag and another bag.
    @param anotherBag The bag that is to be added.
    @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public BagInterface<T> union(BagInterface<T> anotherBag) {
        BagInterface<T> unionBag = new LinkedBag<>();
        T[] bagArray = this.toArray();

        for (T entry : bagArray) {
            if(entry!=null)
                unionBag.add(entry);
        }

        for (T entry : anotherBag.toArray()) {
            if(entry!=null)
                unionBag.add(entry);
        }

        return unionBag;
    }   //end union

    /** Retrieves the intersection of this bag and another bag.
     * @param anotherBag The bag to compare to the contents of this bag.
     * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public BagInterface<T> intersection(BagInterface<T> anotherBag) {
        BagInterface<T> intersectionBag = new LinkedBag<>();
        T[] bagArray = this.toArray();
    
        for (T entry : bagArray) {
            if (entry != null && anotherBag.contains(entry)) {
                intersectionBag.add(entry);
                anotherBag.remove(entry);  // remove the item from the other bag
            }
        }
    
        return intersectionBag;
    }   //end intersection

    /** Retrieves the difference of this bag and another bag.
     * @param anotherBag The bag to compare to the contents of this bag.
     * @return A newly allocated array of all the entries in the bag. Note: If the bag is empty, the returned array is empty. */
    public BagInterface<T> difference(BagInterface<T> anotherBag) {
        BagInterface<T> differenceBag = new LinkedBag<>();
        T[] bagArray = this.toArray();
    
        for (T entry : bagArray) {
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
    }   //end difference
}


    
