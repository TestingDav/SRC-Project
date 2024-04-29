public class calculateImportanceTest {
    public static void main(String[] args) 
    {
        /**
         * This test case will test the calculateImportance and updateResourcePriorityList methods. To do so, we will simulate
         * how the farm may use the program to manage their resources.
         */
        FarmMainClass farm = new FarmMainClass();

        /**
         * Add resources.
         */
        farm.addResource("goat feed", 20, 25, 40);
        farm.addResource("chicken feed", 30, 40, 50);
        farm.addResource( "bunny feed", 40, 30, 25);

        /**
         * Calculate the importance of each method using calculateImportance with a budget of 1000 dollars.
         */
        farm.calculateImportance(1000);

        /**
         * Print resource priority list which returns goat feed and chicken feed as being suggested
         * to be bought. 
         */
        farm.printResourcePriorityList();

        /**
         * Edit resources.
         */
        farm.editResource( "goat feed", 20, 25, 25);
        farm.editResource( "chicken feed", 30, 40, 50);
        farm.editResource("bunny feed", 40, 30, 50);

        /**
         * After editing the resources and running calculateImportance again. The item suggested
         * to be bought is bunny feed. This is because bunny feed needs the most after taking
         * the difference between quanityNeeded and currentQuanity. After multiply the difference 
         * by price, no other items can be bought in full with the budget. This inherently proves
         * updatePriorityList also works.
         */
        System.out.println();
        farm.calculateImportance(1000);
   
        farm.printResourcePriorityList();    
    }
}
