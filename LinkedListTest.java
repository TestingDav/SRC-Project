
import org.junit.Test;

import social.ListInterface;
import social.LinkedList;

import static org.junit.Assert.*;
public class LinkedListTest {
    @Test
    public void testClear() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        list.clear();
        assertEquals(0, list.getLength());
    }

    @Test
    public void testAdd() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        assertEquals("car", list.getEntry(1));
        assertEquals("house", list.getEntry(2));
        assertEquals("hat", list.getEntry(3));
    }

    @Test
    public void testAddAtPosition() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        list.add(2, "dog");
        assertEquals("car", list.getEntry(1));
        assertEquals("dog", list.getEntry(2));
        assertEquals("house", list.getEntry(3));
        assertEquals("hat", list.getEntry(4));
    }

    @Test
    public void testIsEmpty() {
        ListInterface<String> list = new LinkedList<>();
        assertTrue(list.isEmpty());

        list.add("car");
        assertFalse(list.isEmpty());
    }

    @Test
    public void testToArray() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        Object[] array = list.toArray();
        assertEquals("car", array[0]);
        assertEquals("house", array[1]);
        assertEquals("hat", array[2]);
    }

    @Test
    public void testRemoveByPosition() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        String removed = list.remove(2);
        assertEquals("house", removed);
        assertEquals("car", list.getEntry(1));
        assertEquals("hat", list.getEntry(2));
    }

    @Test
    public void testReplace() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        String replaced = list.replace(2, "dog");
        assertEquals("house", replaced);
        assertEquals("car", list.getEntry(1));
        assertEquals("dog", list.getEntry(2));
        assertEquals("hat", list.getEntry(3));
    }

    @Test
    public void testContains() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        assertTrue(list.contains("car"));
        assertTrue(list.contains("house"));
        assertTrue(list.contains("hat"));
        assertFalse(list.contains("dog"));
    }

    @Test
    public void testGetLength() {
        ListInterface<String> list = new LinkedList<>();
        assertEquals(0, list.getLength());

        list.add("car");
        assertEquals(1, list.getLength());

        list.add("house");
        assertEquals(2, list.getLength());
    }

    @Test
    public void testGetEntry() {
        ListInterface<String> list = new LinkedList<>();
        list.add("car");
        list.add("house");
        list.add("hat");

        assertEquals("car", list.getEntry(1));
        assertEquals("house", list.getEntry(2));
        assertEquals("hat", list.getEntry(3));
    }


}
