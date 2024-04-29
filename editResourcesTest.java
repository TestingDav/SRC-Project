package social;
public class editResourcesTest 
{
        public static void main(String[] args) 
        {
                /**
                 * This test case will test the basics: addResource, removeResource, and editResource method. To do so, we will simulate
                 * how the farm may use the program to manage resources for their animals.
                 */
                FarmMainClass farm = new FarmMainClass();
                farm.addResource("goat feed", 20, 25, 40);
                farm.addResource("bunny feed", 15, 20, 20);
                farm.addResource("chicken feed", 15, 25, 30);

                farm.printResources();

                /**
                 * Prices may change. Therefore, we will edit the resources and also fully restock them.
                 */

                farm.editResource("goat feed", 25, 40, 40);
                farm.editResource("bunny feed", 20, 20, 20);
                farm.editResource("chicken feed", 15, 30, 30);

                System.out.println();
                farm.printResources();

                /**
                 * Now we will remove a resource.
                 */
                
                farm.removeResource("bunny feed");

                System.out.println();
                farm.printResources();

                /**
                 * Now we will add a new resource.
                 */

                farm.addResource("pig feed", 30, 40, 50);

                System.out.println();
                farm.printResources();
                

        }
}
