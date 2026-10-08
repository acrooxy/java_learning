public class Week3Activity2 {
    public static void main(String[] args) {
        class Inventory{
            String ProductDetails;
            int Invoice;
            String StockDetails;

            void displayProd(){
                System.out.println("Product Details: " + ProductDetails);
                System.out.println("Invoice: " + Invoice);
                System.out.println("Stock Details: " + StockDetails);
            }

        }
        Inventory Inventory1 = new Inventory();
        Inventory1.Invoice = 767682;
        Inventory1.StockDetails = "Bookshelf ";
        Inventory1.ProductDetails = "Width: 120, Height: 165, Depth: 42, Color: Dark Oak";


    Inventory1.displayProd();
    }
}
