public class Main
{
    public static void main(String[] args)
    {
        Product product = new Product();
        product.set_name("Laptop");
        product.setId(1);
        product.set_description("Apple");
        product.set_price(39999);
        product.set_stockAmonunt(100);
        product.getId();
        product.setId(77);

        ProductManager productManager =new  ProductManager();
        productManager.Add(product);
        System.out.println(product.get_kod());
    }

}