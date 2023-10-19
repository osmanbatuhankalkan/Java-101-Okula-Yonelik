public class Main
{
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
        //Classlar ilgili özellikleri,operasyonları tutarlar. Bir class içerisnde başka bir class kullanılabilir.
        //SOLID kurallarına uyamak adına biz 3 ayrı class sayfası açtık. Bir classın tek bir iş bir if in tek bir iş vb yapması gerekir.
        //Bunlar tek bir sayfada yazılmamalı ayrı ayrı yazılmalıdır. üründe ürün, ürün mngr da baska seyler tutulmalıdır.
    }
}
