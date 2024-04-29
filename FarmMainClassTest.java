public class FarmMainClassTest {
    public static void main(String[] args) {
       
        FarmMainClass farm = new FarmMainClass();
        
        //this is adding resources to the farm
        farm.addResource("Soil",.20, 1, 100);
        farm.addResource("Seeds", .30, 50, 200);
        farm.addResource("Feed", 10, 4, 10);

        //prints the resources of the farm
        System.out.println("Printing Resources:");
        farm.printResources();

        //printes the total price of the resources
        System.out.println("\nPrinting Resource Total Price:");
        farm.printResourceTotalPrice();

        //prints the list of resources in order of priority
        System.out.println("\nPrinting Resource Priority List:");
        farm.printResourcePriorityList();
    }
}
