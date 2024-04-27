package social; 
import java.util.HashMap;
import java.util.Scanner;
public class FarmMainClass {
    private ResizableArrayBag<String> resourceBag;
    private int quantityNeeded;
    private HashMap<String, Double> resources; //resource name, prices
    private HashMap<String, Double> resourceTotalPrice; //resource name, total price for quantity needed
    private HashMap<String, Double> resourceImportance; //resource name, importance
    private LinkedList<String> resourcePriorityList; //list of resources in order of importance

    public FarmMainClass() {
        this.quantityNeeded = 0;
        this.resources = new HashMap<String, Double>();
        resourcePriorityList = new LinkedList<String>();
        resourceBag = new ResizableArrayBag<String>();
        resourceImportance = new HashMap<String, Double>();
    }

    public FarmMainClass(int quantityNeeded, ResizableArrayBag<String> resourceBag) {
        this.quantityNeeded = quantityNeeded;
        this.resourceBag = resourceBag;
        this.resources = new HashMap<String, Double>();
        this.resourceTotalPrice = new HashMap<String, Double>();
        this.resourceImportance = new HashMap<String, Double>();
        resourcePriorityList = new LinkedList<String>();
    }
    
    public void changeQuantityNeeded(int quantityNeeded) {
        this.quantityNeeded = quantityNeeded;
    }

    public void addResourceToBag(String resource) {
        resourceBag.add(resource);
    }

    public void removeResourceFromBag(String resource) {
        resourceBag.remove(resource);
    }

    public void addResourceToMap(String resourceName, double price) {
        resources.put(resourceName, price);
    }

    public void removeResourceFromMap(String resourceName) {
        resources.remove(resourceName);
    }

    public void editResourcePrice(String resourceName, double price) {
        resources.put(resourceName, price);
    }

    public void calculateResourceImportance() {
        for (String resourceName : resources.keySet()) {
            double currentStock = resources.get(resourceName);
            resourceImportance.put(resourceName,currentStock/100);
        }
    }

    public void calculateTotalPrice() {
        for (String resourceName : resources.keySet()) {
            double totalPrice = resources.get(resourceName) * quantityNeeded;
            resourceTotalPrice.put(resourceName, totalPrice);
        }
    }

    public boolean need(String resource) {
        return (resourceImportance.get(resource)>=70);        
    }

    public void addResource(String resourceName, double price) {
        resources.put(resourceName, price);
    }

    public void removeResource(String resourceName) {
        resources.remove(resourceName);
    }

    public void editResource(String resourceName, double price) {
        resources.put(resourceName, price);
    }

    public void printResources() {
        for (String resourceName : resourceBag.toArray()) {
            System.out.println(resourceName + " : " + resources.get(resourceName));
        }
    }

    public void printResourceTotalPrice() {
        for (String resourceName : resourceTotalPrice.keySet()) {
            System.out.println(resourceName + " : " + resourceTotalPrice.get(resourceName));
        }
    }

    public void printResourcePriorityList() {
        for (String resourceName : resourceImportance.keySet()) {
            System.out.println(resourceName + " : " + resourceImportance.get(resourceName));
        }
    }

    public void calculateImportance() {
    }

    public void importance(HashMap<String, Double> resource) {
        /** 
        * The quantity in stock of each resource will be divided by the quantity in which each resource is fully stocked.
        * This division will give us a number which will tell the percentage of how stocked each resource is and therefore allow 
        * us to prioritize each resource/let the farm know which resources need to be restocked.
        */ 
        //Fix vv
        double fullStock = 100;
        for (String resourceName : resource.keySet()) {
            double currentStock = resource.get(resourceName);
            double currentStockPercentage = currentStock/fullStock;
            if (currentStockPercentage < 0.5) {
                importance = 100;
            } else if (currentStockPercentage >= 0.5 && currentStockPercentage < 0.75) {
                importance = 75;
            } else if (currentStockPercentage >= 0.75 && currentStockPercentage < 1) {
                importance = 50;
            } else {
                importance = 25;
            }
        }
    }


    public static void main(String[] args) {
        HashMap<String, Double> rNow = new HashMap<String, Double>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add resource");
            System.out.println("2. Remove a resource");
            System.out.println("3. Set the price of a resource");
            System.out.println("4. Check the price of all resource");
            System.out.println("5. Edit quantity of a resource");
            System.out.println("6. Check the quani");
            System.out.println("7. View all resources and their importance for purchase");
            System.out.println("8. Compare budget with the purchase cost of only important resources");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            //System.out (check certain resource purchase cost for #)
            //check resource needed
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over

            switch(choice) {
                case 1:
                    System.out.print("Enter the name of the resource: ");
                    String resourceName = scanner.nextLine();
                    System.out.print("Enter the price of the resource: ");
                    double price = scanner.nextDouble();
                    rNow.put(resourceName, price);
                    break;
                case 2:
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    rNow.remove(resourceName);
                    break;
                case 3:
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    System.out.print("Enter the new price of the resource: ");
                    price = scanner.nextDouble();
                    rNow.put(resourceName, price);
                    break;
                case 4:
                    for (String resource : rNow.keySet()) {
                        System.out.println(resource + " : " + rNow.get(resource));
                    }
                    break;
                case 5:
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    System.out.print("Enter the new quantity of the resource: ");
                    double quantity = scanner.nextDouble();
                    rNow.put(resourceName, quantity);
                    break;
                case 6:
                    for (String resource : rNow.keySet()) {
                        System.out.println(resource + " : " + rNow.get(resource));
                    }
                    break;
                case 7:
                    for (String resource : rNow.keySet()) {
                        System.out.println(resource + " : " + rNow.get(resource));
                    }
                    break;
                case 8:
                    for (String resource : rNow.keySet()) {
                        System.out.println(resource + " : " + rNow.get(resource));
                    }
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            scanner.close();
            System.out.println();
        }
    }
}
