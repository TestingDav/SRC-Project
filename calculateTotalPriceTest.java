public class calculateTotalPriceTest {
    public static void main(String[] args) 
    {
            /**
         * This test case will test calculateTotalPrice method. To do so, we will simulate
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
         * Calcuate the expected total price by multiplying the quanity needed and price
         */
            double goatFeedExpectedPrice = 20 * 40;
            double chickenFeedExpectedPrice = 30 * 50;
            double bunnyFeedExpectedPrice = 40 * 25;

            System.out.println("Expexcted prices:");
            System.out.println("goat feed = " + goatFeedExpectedPrice);
            System.out.println("chicken feed = " + chickenFeedExpectedPrice);
            System.out.println("bunny feed = " + bunnyFeedExpectedPrice);

            System.out.println();
    
            /**
         * Print the resoruces using printResourceTotalPrice.
         */
            farm.printResourceTotalPrice();
     }
}