
import org.junit.Test;

import social.BagInterface;
import social.ResizableArrayBag;

import static org.junit.Assert.*;
public class ResizableArrayBagTest{

    @Test
    public void testGetCurrentSize() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        assertEquals(0, bag.getCurrentSize());

        bag.add("car");
        assertEquals(1, bag.getCurrentSize());

        bag.add("house");
        assertEquals(2, bag.getCurrentSize());
    }

    @Test
    public void testIsEmpty() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        assertTrue(bag.isEmpty());

        bag.add("car");
        assertFalse(bag.isEmpty());
    }

    @Test
    public void testAdd() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        assertTrue(bag.add("car"));
        assertTrue(bag.add("house"));
        assertTrue(bag.add("hat"));
    }

    @Test
    public void testRemove() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("hat");

        assertEquals("hat", bag.remove());
        assertEquals("house", bag.remove());
        assertEquals("car", bag.remove());
        assertNull(bag.remove());
    }

    @Test
    public void testRemoveEntry() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("hat");

        assertTrue(bag.remove("car"));
        assertFalse(bag.remove("plane"));
    }

    @Test
    public void testClear() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("hat");

        bag.clear();
        assertTrue(bag.isEmpty());
        assertEquals(0, bag.getCurrentSize());
    }

    @Test
    public void testGetFrequencyOf() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("car");
        bag.add("hat");

        assertEquals(2, bag.getFrequencyOf("car"));
        assertEquals(1, bag.getFrequencyOf("house"));
        assertEquals(0, bag.getFrequencyOf("plane"));
    }

    @Test
    public void testContains() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("hat");

        assertTrue(bag.contains("car"));
        assertTrue(bag.contains("house"));
        assertTrue(bag.contains("hat"));
        assertFalse(bag.contains("plane"));
    }

    @Test
    public void testToArray() {
        BagInterface<String> bag = new ResizableArrayBag<>();
        bag.add("car");
        bag.add("house");
        bag.add("hat");
        Object[] expected = {"car", "house", "hat"};
        assertArrayEquals(expected, bag.toArray());    
    }

    @Test
    public void testUnion() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        bag1.add("car");
        bag1.add("house");

        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag2.add("plane");

        BagInterface<String> unionBag = bag1.union(bag2);
        assertTrue(unionBag.contains("house"));
        assertTrue(unionBag.contains("car"));
    }

    @Test
    public void testIntersection() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        bag1.add("car");
        bag1.add("house");
        bag1.add("hat");

        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag2.add("house");
        bag2.add("hat");
        bag2.add("plane");

        BagInterface<String> intersectionBag = bag1.intersection(bag2);
        assertTrue(intersectionBag.contains("house"));
        assertTrue(intersectionBag.contains("hat"));
        assertFalse(intersectionBag.contains("car"));
        assertFalse(intersectionBag.contains("plane"));

    }

    @Test
    public void testDifference() {
        BagInterface<String> bag1 = new ResizableArrayBag<>();
        bag1.add("car");
        bag1.add("house");
        bag1.add("hat");

        BagInterface<String> bag2 = new ResizableArrayBag<>();
        bag2.add("house");
        bag2.add("hat");
        bag2.add("plane");

        BagInterface<String> differenceBag = bag1.difference(bag2);
        assertTrue(differenceBag.contains("car"));
        assertFalse(differenceBag.contains("house"));
        assertFalse(differenceBag.contains("hat"));
        assertFalse(differenceBag.contains("plane"));
    }
}
