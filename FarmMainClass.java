package social; 
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.InputMismatchException;
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

    // Constructor
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

    /**
     * Check if the resource already exists in the resource bag
     * @param resourceName
     * @return true if the resource doesn't exist, false otherwise
     */
    public boolean checkAdd(String resourceName) {
        for (String resource : resourceBag.toArray(new String[0])) {
            if (resource.equalsIgnoreCase(resourceName)) {
                System.out.println("Resource already exists.");
                return false;
            }
        }   return true;
    }

    /**
     * Add a resource to the resource bag
     * @param resourceName
     * @param price
     * @param currentQuantity
     * @param quantityNeeded
     */
    public void addResource(String resourceName, double price, double currentQuantity, double quantityNeeded) {
        resources.put(resourceName, price);
        resourceQuantityCurrent.put(resourceName, currentQuantity);
        resourceQuantityNeeded.put(resourceName, quantityNeeded);
        calculateTotalPrice(resourceName);
        calculateImportance(budget);
        resourceBag.add(resourceName);
    }

    /**
     * Remove a resource from the resource bag
     * @param resourceName
     */
    public void removeResource(String resourceName) {
        if(!resourceBag.contains(resourceName)){
            System.out.println("Resource doesn't exist.");
            return;
        }
        resources.remove(resourceName);
        resourceQuantityCurrent.remove(resourceName);
        resourceQuantityNeeded.remove(resourceName);
        resourceTotalPrice.remove(resourceName);
        resourceImportance.remove(resourceName);
        resourceBag.remove(resourceName);
    }

    /**
     * Check if the resource exists in the resource bag
     * @param resourceName
     * @return true if the resource exists, false otherwise
     */
    public boolean checkEdit(String resourceName) {
        for (String resource : resourceBag.toArray(new String[0])) {
            if (!resource.equalsIgnoreCase(resourceName)) {
                System.out.println("Resource doesn't exist.");
                return false;
            }
        }   return true;
    }

    /**
     * Edit a resource in the resource bag
     * @param resourceName
     * @param price
     * @param currentQuantity
     * @param quantityNeeded
     */
    public void editResource(String resourceName, double price, double currentQuantity, double quantityNeeded) {
        resources.put(resourceName, price);
        resourceQuantityCurrent.put(resourceName, currentQuantity);
        resourceQuantityNeeded.put(resourceName, quantityNeeded);
        calculateTotalPrice(resourceName);
        calculateImportance(budget);
    }

    /**
     * Calculate the total price for the quantity needed of a resource
     * @param resourceName
     */
    public void calculateTotalPrice(String resourceName) {
        double price = resources.get(resourceName);
        double quantityNeeded = resourceQuantityNeeded.get(resourceName);
        double totalPrice = price * quantityNeeded;
        resourceTotalPrice.put(resourceName, totalPrice);
    }

    /**
     * Calculate the importance of each resource based on the budget
     * @param budget
     */
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

    /**
     * Update the resource priority list based on the importance of each resource
     */
    public void updateResourcePriorityList() {
        resourcePriorityList.clear();
        resourceImportance.entrySet().stream()
            .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
            .forEachOrdered(x -> resourcePriorityList.add(x.getKey()));
    }

    /**
     * Print all resources
     */
    public void printResources() {
        for (String resourceName : resources.keySet()) {
            System.out.println("Resource Name: " + resourceName + ", Price: " + resources.get(resourceName) + ", Current Quantity: " + resourceQuantityCurrent.get(resourceName) + ", Quantity Needed: " + resourceQuantityNeeded.get(resourceName));
        }
    }

    /**
     * Print the total price of all resources
     */
    public void printResourceTotalPrice() {
        for (String resourceName : resourceTotalPrice.keySet()) {
            System.out.println("Resource Name: " + resourceName + ", Total Purchase Price: " + resourceTotalPrice.get(resourceName));
        }
    }

    /**
     * Print the priority list of resources
     */
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
        // Create a new farm instance
        FarmMainClass farm = new FarmMainClass();
        Scanner scanner = new Scanner(System.in);
        // Main menu
        while (true) {
            // Display the menu
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
            // Perform the selected action using switch cases
            switch (choice) {
                case 1:
                    // Add a resource
                    System.out.print("Enter the name of the resource: ");
                    String resourceName = scanner.nextLine();
                    // Check if the resource already exists
                    if (farm.checkAdd(resourceName)){
                        // If the resource doesn't exist, add it
                        System.out.print("Enter the price of the resource: ");
                        try {
                            double price = scanner.nextDouble();
                            System.out.print("Enter the current quantity of the resource: ");
                            double currentQuantity = scanner.nextDouble();
                            System.out.print("Enter the quantity needed of the resource: ");
                            double quantityNeeded = scanner.nextDouble();
                            farm.addResource(resourceName, price, currentQuantity, quantityNeeded);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine(); // clear the scanner
                        }
                    }
                    break;
                case 2:
                    // Remove a resource
                    if(farm.resources.isEmpty()){
                        // Check if there are any resources to remove
                        System.out.println("Please add resources first.");
                        break;
                    } else {
                        // If there are resources, remove the selected resource
                        System.out.print("Enter the name of the resource: ");
                        resourceName = scanner.nextLine();
                        farm.removeResource(resourceName);
                        break;
                    }
                case 3:
                    // Edit a resource
                    System.out.print("Enter the name of the resource: ");
                    resourceName = scanner.nextLine();
                    // Check if the resource exists
                    if (farm.checkEdit(resourceName)){
                        // If the resource exists, edit it
                        System.out.print("Enter the new price of the resource: ");
                        try {
                            double price = scanner.nextDouble();
                            System.out.print("Enter the new current quantity of the resource: ");
                            double currentQuantity = scanner.nextDouble();
                            System.out.print("Enter the new quantity needed of the resource: ");
                            double quantityNeeded = scanner.nextDouble();
                            farm.editResource(resourceName, price, currentQuantity, quantityNeeded);
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            scanner.nextLine(); // clear the scanner
                        }
                    }
                    break;
                case 4:
                    // Print all resources
                    if(farm.resources.isEmpty()){
                        // Check if there are any resources to print
                        System.out.println("Please add resources first.");
                        break;
                    }
                    // If there are resources, print them
                    farm.printResources();
                    break;
                case 5:
                    // Print the total price of all resources
                    if(farm.resources.isEmpty()){
                        // Check if there are any resources to print
                        System.out.println("Please add resources first.");
                        break;
                    }
                    // If there are resources, print the total price
                    farm.printResourceTotalPrice();
                    break;
                case 6:
                    // Print the priority list of resources
                    if(farm.budget == 0){
                        // Check if the budget is set
                        System.out.println("Please enter your total budget first.");
                        break;
                    }
                    // If the budget is set, print the priority list
                    System.out.println("With your budget of " + farm.budget + ", the following suggestions are: ");
                    farm.printResourcePriorityList();
                    break;
                case 7:
                    // Enter the total budget
                    System.out.print("Enter your total budget: ");
                    try {
                        int budget = scanner.nextInt();
                        farm.budget = budget;
                        // Calculate the importance of each resource based on the budget
                        farm.calculateImportance(budget);
                    } catch (InputMismatchException e) {
                        System.out.println("Invalid input. Please enter a number.");
                        scanner.nextLine(); // clear the scanner
                    }
                    break;
                case 0:
                    // Exit the program
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    // Invalid choice
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
                }
            }
}
