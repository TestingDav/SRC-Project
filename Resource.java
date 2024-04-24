public class Resource 
{
        private String resourceName;
        private int quantityFullyStocked;
        private int quantityStocked;
        private int price;
        private double currentStockPercentage;

        public Resource()
        {
                resourceName = null;
                quantityFullyStocked = 0;
                quantityStocked = 0;
                currentStockPercentage = 0;
                price = 0;
        }

        public Resource(int quantityFullyStocked, int quantityStocked, int price, String resourceName)
        {
                this.quantityFullyStocked = quantityFullyStocked;
                this.quantityStocked = quantityStocked;
                currentStockPercentage = ((double)quantityStocked / quantityFullyStocked) * 100;
                this.price = price;
                this.resourceName = resourceName;
        }

        public void setPrice(int price)
        {
                this.price = price;
        }

        public void setQuantityFullyStocked(int quantityFullyStocked)
        {
                this.quantityFullyStocked = quantityFullyStocked;
        }

        public void setQuantityStocked(int quantityStocked)
        {
                this.quantityStocked = quantityStocked;
        }

        public void setcurrentStockPercentage(double currentStockPercentage)
        {
                this.currentStockPercentage = currentStockPercentage;
        }

        public int getPrice()
        {
                return price;
        }

        public int getQuantityFullyStocked()
        {
                return quantityFullyStocked;
        }

        public int getQuantityStocked()
        {
                return quantityStocked;
        }

        public double getcurrentStockPercentage()
        {
                return currentStockPercentage;
        }

        public String toString()
        {
                return String.format("%s : %.2f%% stocked" , resourceName, currentStockPercentage);
        }
          
}
