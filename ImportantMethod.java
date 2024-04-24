public class ImportantMethod
{

          public static void important(LinkedBag<Resource> resource)
          {
                    Resource[] resources = resource.toArray();
                    /** 
                    * The quantity in stock of each resource will be divided by the quantity in which each resource is fully stocked.
                    * This division will give us a number which will tell the percentage of how stocked each resource is and therefore allow 
                    * us to prioritize each resource/let the farm know which resources need to be restocked.
                    */ 

                    for(int i = 0 ; i < resources.length ; i++)
                    {
                              for(int j = i + 1 ; j < resources.length ; j++)
                              {
                                        Resource greatestPriority = null;
                                        if(resources[i].getcurrentStockPercentage() > resources[j].getcurrentStockPercentage())
                                        {
                                                  greatestPriority = resources[i];
                                                  resources[i] = resources[j];
                                                  resources[j] = greatestPriority;

                                        }
                              }
                    }

                    LList<Resource> resourcePriorityList = new LList<Resource>();
                    for(int i = 0 ; i < resources.length ; i++)
                    {
                              resourcePriorityList.add(resources[i]);
                    }
          }
}