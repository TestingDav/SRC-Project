import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import social.FarmMainClass;
public class FarmMainClassTest {
    private FarmMainClass farm;

    @Before
    public void setUp() {
        farm = new FarmMainClass();
    }

    @Test
    public void testAddResource() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        assertTrue(farm.resources.containsKey("Seeds"));
        assertEquals(10.0, farm.resources.get("Seeds"), 0.01);
    }

    @Test
    public void testRemoveResource() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        farm.removeResource("Seeds");
        assertFalse(farm.resources.containsKey("Seeds"));
    }

    @Test
    public void testEditResource() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        farm.editResource("Seeds", 20.0, 10.0, 15.0);
        assertEquals(20.0, farm.resources.get("Seeds"), 0.01);
        assertEquals(10.0, farm.resourceQuantityCurrent.get("Seeds"), 0.01);
        assertEquals(15.0, farm.resourceQuantityNeeded.get("Seeds"), 0.01);
    }

    @Test
    public void testCalculateTotalPrice() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        farm.calculateTotalPrice("Seeds");
        assertEquals(50.0, farm.resourceTotalPrice.get("Seeds"), 0.01);
    }

    @Test
    public void testCalculateImportance() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        farm.addResource("Fertilizer", 20.0, 2.0, 5.0);
        farm.budget = 200;
        farm.calculateImportance(farm.budget);
        assertTrue(farm.resourceImportance.containsKey("Seeds"));
        assertTrue(farm.resourceImportance.containsKey("Fertilizer"));
    }

    @Test
    public void testUpdateResourcePriorityList() {
        farm.addResource("Seeds", 10.0, 5.0, 10.0);
        farm.addResource("Fertilizer", 20.0, 2.0, 5.0);
        farm.budget = 200;
        farm.calculateImportance(farm.budget);
        farm.updateResourcePriorityList();
        assertEquals(2, farm.resourcePriorityList.size());
    }
}
