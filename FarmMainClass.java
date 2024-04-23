package social; // could change the importance to a stack
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
public class FarmMainClass {
    private String season;
    private int quantityNeeded;
    private LinkedBag<String> resource;//change to dictionary
    private double importance; //1-100
    private double seasonWeight; // 1.5 for winter, 1.2 for spring, 1 for summer, 1.4 for fall (for example)
    //create a bag of prices (a dictionary of prices for each resources) x wouldnt work if we need to find the price of a specfic resource. solution: ...
    private HashMap<String, Double> prices;
    public FarmMainClass() {
        this.season = "winter";
        this.quantityNeeded = 0;
        this.resource = new LinkedBag<String>();
        this.resource = null; // or something else
        this.seasonWeight = (1.5+1.2+1+1.4)/4;
    }

    public FarmMainClass(String season, int quantityNeeded, LinkedBag<String> resource) {
        this.season = season;
        this.quantityNeeded = quantityNeeded;
        this.resource = resource;
        this.resource = null; // or something else
        if(season.equalsIgnoreCase("winter")) {
            this.seasonWeight = 1.5;
        } else if(season.equalsIgnoreCase("spring")) {
            this.seasonWeight = 1.2;
        } else if(season.equalsIgnoreCase("summer")) {
            this.seasonWeight = 1;
        } else if(season.equalsIgnoreCase("fall")) {
            this.seasonWeight = 1.4;
        }
    }
    
    public List<String> estimateCost(String resourceRequest, int quantityNeeded, HashMap<String, Double> resource, double seasonWeight) {
        return null;
    }

    public boolean need(LinkedBag<String> resource) {
        return importance >= 65;
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
            //System.out (check certain resource purchase cost for #)
            //check resource needed
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over
            //add cases
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
