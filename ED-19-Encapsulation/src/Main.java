public class Main {
    public static void main(String[] args)
    {
        Product product = new Product();

        product.name="Laptop";
        System.out.println(product.name);

        product.id = 1;
        System.out.println(product.id);

        product.description ="Apple";
        System.out.println(product.description);

        product.price=38000;
        System.out.println(product.price);

        product.stockAmonut=3;
        System.out.println(product.stockAmonut);

        System.out.println("*********************************");

        ProductManager productManager = new ProductManager();
        productManager.Add(product);

        productManager.Add2(1," ","",5,500);
        productManager.Add2(1," ","",5,500);
        productManager.Add2(1," ","",5,500);
        productManager.Add2(1," ","",5,500);
    }
}