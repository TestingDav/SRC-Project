package social;

public class LinkedList<T> implements ListInterface<T> {
	private Node firstNode;       // Head reference to first node
   private Node lastNode;        // Tail reference to last node
   private int  numberOfEntries; // Number of entries in list

	public LinkedList() {
		initializeDataFields();
	} // end default constructor
	
   /** Adds a new entry to the end of this list.
       Entries currently in the list are unaffected.
       The list's size is increased by 1.
       @param newEntry  The object to be added as a new entry. */
	public void clear()	{
		initializeDataFields();
	} // end clear

   /** Adds a new entry to the end of this list.
       Entries currently in the list are unaffected.
       The list's size is increased by 1.
       @param newEntry  The object to be added as a new entry. */
   public void add(int givenPosition, T newEntry){
   if ((givenPosition >= 1) && (givenPosition <= numberOfEntries + 1))
   {
      Node newNode = new Node(newEntry);
      if (isEmpty())
      {
         firstNode = newNode;
         lastNode = newNode;
      }
      else if (givenPosition == 1)
      {
         newNode.setNextNode(firstNode);
         firstNode = newNode;
      }
      else if (givenPosition == numberOfEntries + 1)
      {
         lastNode.setNextNode(newNode);
         lastNode = newNode;
      }
      else
      {
         Node nodeBefore = getNodeAt(givenPosition - 1);
         Node nodeAfter = nodeBefore.getNextNode();
         newNode.setNextNode(nodeAfter);
         nodeBefore.setNextNode(newNode);
      } // end if
      numberOfEntries++;
   }
   else
      throw new IndexOutOfBoundsException(
                "Illegal position given to add operation.");
} // end add

   /** Removes one occurrence of a given entry from this list,
       if possible.
       Entries located after the removed entry have their
       positions decreased by 1.
       @param anEntry  The object to be removed.
       @return  True if anEntry was located and removed; 
                otherwise returns false. */
   public boolean isEmpty() {
      boolean result;
         
      if (numberOfEntries == 0) // Or getLength() == 0
      {
         // Assertion: firstNode == null
         result = true;
      }
      else
      {
         // Assertion: firstNode != null
         result = false;
      } // end if
         
      return result;
   } // end isEmpty

   /** Removes one occurrence of a given entry from this list,
       if possible.
       Entries located after the removed entry have their
       positions decreased by 1.
       @param anEntry  The object to be removed.
       @return  True if anEntry was located and removed; 
                otherwise returns false. */
   public T[] toArray() {
         // The cast is safe because the new array contains null entries
         @SuppressWarnings("unchecked")
         T[] result = (T[])new Object[numberOfEntries];
         
         int index = 0;
         Node currentNode = firstNode;
         while ((index < numberOfEntries) && (currentNode != null))
         {
            result[index] = currentNode.getData();
            currentNode = currentNode.getNextNode();
            index++;
         } // end while
         
         return result;
   } // end toArray

   /** Removes one occurrence of a given entry from this list,
       if possible.
       Entries located after the removed entry have their
       positions decreased by 1.
       @param anEntry  The object to be removed.
       @return  True if anEntry was located and removed; 
                otherwise returns false. */
   public T remove(int givenPosition) {
      T result = null;                           // Return value
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
      // Assertion: !isEmpty()
         if (givenPosition == 1)                 // Case 1: Remove first entry
         {
            result = firstNode.getData();        // Save entry to be removed
            firstNode = firstNode.getNextNode();
            if (numberOfEntries == 1)
               lastNode = null;                  // Solitary entry was removed
         }
         else                                    // Case 2: Not first entry
         {
            Node nodeBefore = getNodeAt(givenPosition - 1);
            Node nodeToRemove = nodeBefore.getNextNode();
            Node nodeAfter = nodeToRemove.getNextNode();
            nodeBefore.setNextNode(nodeAfter);
            result = nodeToRemove.getData();
            if (givenPosition == numberOfEntries)
               lastNode = nodeBefore;            // Last node was removed
         } // end if
         numberOfEntries--;
      }
      else
         throw new IndexOutOfBoundsException(
                  "Illegal position given to remove operation.");

      return result;                             // Return removed entry
   } // end remove

   /** Replaces the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the entry to be replaced.
       @param newEntry  The object that will replace the entry at the
                        position givenPosition.
       @return  The original entry that was replaced. */
   public T replace(int givenPosition, T newEntry) {
      if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
      {
         // Assertion: !isEmpty()
         Node desiredNode = getNodeAt(givenPosition);
         T originalEntry = desiredNode.getData();
         desiredNode.setData(newEntry);
         return originalEntry;
      }
      else
         throw new IndexOutOfBoundsException(
                  "Illegal position given to replace operation.");
   } // end replace

   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   public boolean contains(T anEntry)
   {
      boolean found = false;
      Node currentNode = firstNode;
      
      while (!found && (currentNode != null))
      {
         if (anEntry.equals(currentNode.getData()))
            found = true;
         else
            currentNode = currentNode.getNextNode();
      } // end while
      
      return found;
   } // end contains


   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   public int getLength()
   {
      return numberOfEntries;
   } // end getLength

   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   public void add(T newEntry)
   {
      Node newNode = new Node(newEntry);
      if (isEmpty())
         firstNode = newNode;
      else
         lastNode.setNextNode(newNode);
      lastNode = newNode;
      numberOfEntries++;
   } // end add

   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   public boolean remove(T anEntry)
   {
      boolean result = false;
      Node nodeN = firstNode;
      Node nodeN1 = null;
      while ((nodeN != null) && !result)
      {
         if (anEntry.equals(nodeN.getData()))
            result = true;
         else
         {
            nodeN1 = nodeN;
            nodeN = nodeN.getNextNode();
         } // end if
      } // end while
      if (result)
      {
         Node nodeAfter = nodeN.getNextNode();
         if (nodeN == firstNode)
         {
            firstNode = nodeAfter;
            if (firstNode == null)
               lastNode = null;
         }
         else
         {
            nodeN1.setNextNode(nodeAfter);
            if (nodeN == lastNode)
               lastNode = nodeN1;
         } // end if
         numberOfEntries--;
      } // end if
      return result;
   } // end remove

   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            if (numberOfEntries == 0)
                lastNode = null;
        } // end if
        return result;
    } // end remove

   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
    public T getEntry(int givenPosition)
    {
        if ((givenPosition >= 1) && (givenPosition <= numberOfEntries))
        {
            return getNodeAt(givenPosition).getData();
        }
        else
            throw new IndexOutOfBoundsException(
                    "Illegal position given to getEntry operation.");
    } // end getEntry
  
   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   private void initializeDataFields()
   {
      firstNode = null;
      lastNode = null;
      numberOfEntries = 0;
   } // end initializeDataFields
   
   /** Retrieves the entry at a given position in this list.
       @param givenPosition  An integer that indicates the position of
                             the desired entry.
       @return  A reference to the indicated entry.
       @throws  IndexOutOfBoundsException if either
                givenPosition < 1 or givenPosition > getLength(). */
   private Node getNodeAt(int givenPosition)
   {
      // Assertion: (firstNode != null) &&
      //            (1 <= givenPosition) && (givenPosition <= numberOfEntries)
      Node currentNode = firstNode;
      // Traverse the chain to locate the desired node
      // (skipped if givenPosition is 1)
      for (int counter = 1; counter < givenPosition; counter++)
         currentNode = currentNode.getNextNode();
      // Assertion: currentNode != null
      return currentNode;
   } // end getNodeAt
  
   
	private class Node
	{
      private T    data; // Entry in list
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         data = dataPortion;
         next = null;
      } // end constructor
      
      private Node(T dataPortion, Node nextNode)
      {
         data = dataPortion;
         next = nextNode;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node
} // end LList
