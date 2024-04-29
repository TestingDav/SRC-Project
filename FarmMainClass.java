package social; 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FarmMainClass {
    public ResizableArrayBag<String> resourceBag;
    public HashMap<String, Double> resources; //resource name, prices
    public HashMap<String, Double> resourceQuantityCurrent; //resource name, current quantity
    public HashMap<String, Double> resourceQuantityNeeded; //resource name, quantity needed
    public HashMap<String, Double> resourceTotalPrice; //resource name, total price for quantity needed
    public HashMap<String, Double> resourceImportance; //resource name, importance
    public LinkedList<String> resourcePriorityList; //list of resources in order of importance
    public int budget; //total budget

    public FarmMainClass() {
        this.resources = new HashMap<String, Double>();
        this.resourceQuantityCurrent = new HashMap<String, Double>();
        this.resourceQuantityNeeded = new HashMap<String, Double>();
        this.resourceTotalPrice = new HashMap<String, Double>();
        this.resourceImportance = new HashMap<String, Double>();
        this.resourcePriorityList = new LinkedList<String>();
        this.resourceBag = new ResizableArrayBag<String>();
        this.budget = 0;
    }
    public boolean checkAdd(String resourceName) {
        for (String resource : resourceBag.toArray(new String[0])) {
            if (resource.equalsIgnoreCase(resourceName)) {
                System.out.println("Resource already exists.");
                return false;
            }
        }   return true;
    }

    public void addResource(String resourceName, double price, double currentQuantity, double quantityNeeded) {
        resources.put(resourceName, price);
        resourceQuantityCurrent.put(resourceName, currentQuantity);
        resourceQuantityNeeded.put(resourceName, quantityNeeded);
        calculateTotalPrice(resourceName);
        calculateImportance(budget);
        resourceBag.add(resourceName);
    }

    public void removeResource(String resourceName) {
        resources.remove(resourceName);
        resourceQuantityCurrent.remove(resourceName);
        resourceQuantityNeeded.remove(resourceName);
        resourceTotalPrice.remove(resourceName);
        resourceImportance.remove(resourceName);
        resourceBag.remove(resourceName);
    }

    public boolean checkEdit(String resourceName) {
        for (String resource : resourceBag.toArray(new String[0])) {
            if (!resource.equalsIgnoreCase(resourceName)) {
                System.out.println("Resource doesn't exist.");
                return false;
            }
        }   return true;
    }

    public void editResource(String resourceName, double price, double currentQuantity, double quantityNeeded) {
        resources.put(resourceName, price);
        resourceQuantityCurrent.put(resourceName, currentQuantity);
        resourceQuantityNeeded.put(resourceName, quantityNeeded);
        calculateTotalPrice(resourceName);
        calculateImportance(budget);
    }

    public void calculateTotalPrice(String resourceName) {
        double price = resources.get(resourceName);
        double quantityNeeded = resourceQuantityNeeded.get(resourceName);
        double totalPrice = price * quantityNeeded;
        resourceTotalPrice.put(resourceName, totalPrice);
    }

    

    public void calculateImportance(double budget) {
        // Calculate the total purchase cost for each resource
        Map<String, Double> totalPurchaseCost = new HashMap<>();
        for (Map.Entry<String, Double> entry : resourceQuantityNeeded.entrySet()) {
            String resourceName = entry.getKey();
            double quantityNeeded = entry.getValue();
            double quantityCurrent = resourceQuantityCurrent.get(resourceName);
            double price = resources.get(resourceName);
            double purchaseQuantity = Math.max(0, quantityNeeded - quantityCurrent);
            totalPurchaseCost.put(resourceName, purchaseQuantity * price);
        }
    
        // Sort resources based on total purchase cost in descending order
        List<Map.Entry<String, Double>> sortedResources = new ArrayList<>(totalPurchaseCost.entrySet());
        sortedResources.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
    
        double remainingBudget = budget;
        double totalCostWithinBudget = 0;
        for (Map.Entry<String, Double> entry : sortedResources) {
            double totalCost = entry.getValue();
            if (totalCost <= remainingBudget) {
                totalCostWithinBudget += totalCost;
                remainingBudget -= totalCost;
            }
        }
    
        for (Map.Entry<String, Double> entry : sortedResources) {
            String resourceName = entry.getKey();
            double totalCost = entry.getValue();
    
            double importance;
            if (totalCost <= budget) {
                importance = (totalCost / totalCostWithinBudget) * 100;
            } else {
                importance = (remainingBudget / totalCost) * 100;
                remainingBudget = 0;
            }
    
            resourceImportance.put(resourceName, importance);
        }
    
        updateResourcePriorityList();
    }

    public void updateResourcePriorityList() {
        resourcePriorityList.clear();
        resourceImportance.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEachOrdered(x -> resourcePriorityList.add(x.getKey()));
    }

    public void printResources() {
        for (String resourceName : resources.keySet()) {
            System.out.println("Resource Name: " + resourceName + ", Price: " + resources.get(resourceName) + ", Current Quantity: " + resourceQuantityCurrent.get(resourceName) + ", Quantity Needed: " + resourceQuantityNeeded.get(resourceName));
        }
    }

    public void printResourceTotalPrice() {
        for (String resourceName : resourceTotalPrice.keySet()) {
            System.out.println("Resource Name: " + resourceName + ", Total Purchase Price: " + resourceTotalPrice.get(resourceName));
        }
    }

    public void printResourcePriorityList() {
        for (String resourceName : resourcePriorityList) {
            if(resourceImportance.get(resourceName)>=50){
                System.out.println("Resource Name: " + resourceName + ", Buying is suggested.");
            }
            else{
                System.out.println("Resource Name: " + resourceName + ", Buying is not suggested.");
            }
            System.out.println("Resource Name: " + resourceName + ", Units Needed: " + resourceQuantityNeeded.get(resourceName) + ", Price per Unit: " + resources.get(resourceName) + ", Total Price: " + resourceTotalPrice.get(resourceName));
        }
    }

    public static void main(String[] args) {
        FarmMainClass farm = new FarmMainClass();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add resource");
            System.out.println("2. Remove a resource");
            System.out.println("3. Edit a resource");
            System.out.println("4. Print all resources");
            System.out.println("5. Print total price of all resources");
            System.out.println("6. Print priority list of resources");
            System.out.println("7. Enter your total budget");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline left-over

            switch(choice) {
                case 1:
                    System.out.print("Enter the name of the resource: ");
                    String resourceName = scanner.nextLine();
                    if (farm.checkAdd(resourceName)){
                        System.out.print("Enter the price of the resource: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter the current quantity of the resource: ");
                        double currentQuantity = scanner.nextDouble();
                        System.out.print("Enter the quantity needed of the resource: ");
                        double quantityNeeded = scanner.nextDouble();
                        farm.addResource(resourceName, price, currentQuantity, quantityNeeded);
                    }
                    break;
                case 2:
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    farm.removeResource(resourceName);
                    break;
                case 3:
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    if (farm.checkEdit(resourceName)){
                        System.out.print("Enter the new price of the resource: ");
                        double price = scanner.nextDouble();
                        System.out.print("Enter the new current quantity of the resource: ");
                        double currentQuantity = scanner.nextDouble();
                        System.out.print("Enter the new quantity needed of the resource: ");
                        double quantityNeeded = scanner.nextDouble();
                        farm.editResource(resourceName, price, currentQuantity, quantityNeeded);
                    }
                    break;
                case 4:
                    if(farm.resources.isEmpty()){
                        System.out.println("Please add resources first.");
                        break;
                    }
                    farm.printResources();
                    break;
                case 5:
                    if(farm.resources.isEmpty()){
                        System.out.println("Please add resources first.");
                        break;
                    }
                    farm.printResourceTotalPrice();
                    break;
                case 6:
                    if(farm.budget == 0){
                        System.out.println("Please enter your total budget first.");
                        break;
                    }
                    System.out.println("With your budget of " + farm.budget + ", the following suggestions are: ");
                    farm.printResourcePriorityList();
                    break;
                case 7:
                    System.out.print("Enter your total budget: ");
                    int budget = scanner.nextInt();
                    farm.budget = budget;
                    farm.calculateImportance(budget);
                    break;
                case 0:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        }
    }
}
