package social;
import java.util.Scanner;
public class FarmMainClass {
    private String season;
    private int quantityNeeded;
    private LinkedBag resource;
    public FarmMainClass() {
        this.season = "winter";
        this.quantityNeeded = 0;
        this.resource = new LinkedBag();
        this.resource = null; // or something else
    }

    public FarmMainClass(String season, int quantityNeeded, LinkedBag resource) {
        this.season = season;
        this.quantityNeeded = quantityNeeded;
        this.resource = resource;
        this.resource = null; // or something else
    }

    public double estimateCost(int quantityNeeded, LinkedBag resource) {
        // Placeholder variable for pricing
        double price = 0.0;

        // Calculate estimated cost based on quantity needed and resource
        double estimatedCost = price * quantityNeeded;

        return estimatedCost;
    }

    public boolean need(LinkedBag resource) {
        // Check if a resource is needed
        // Implement logic soon
        return false;
    }
    public static void main(String[] args) {
        LinkedBag<String> bag = new LinkedBag<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add resource");
            System.out.println("2. Remove a resource");
            System.out.println("3. Check number of resources");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over

            switch (choice) {
                case 1:
                    System.out.print("Enter item to add: ");
                    String item = scanner.nextLine();
                    bag.add(item);
                    break;
                case 2:
                    System.out.print("Enter item to remove: ");
                    String itemToRemove = scanner.nextLine();
                    if (bag.remove(itemToRemove)) {
                        System.out.println("Removed " + itemToRemove);
                    } else {
                        System.out.println("Could not find " + itemToRemove);
                    }
                    break;
                case 3:
                    System.out.println("Bag size: " + bag.getCurrentSize());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
