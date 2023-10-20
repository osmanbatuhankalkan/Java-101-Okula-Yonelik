public class Main {
    public static void main(String[] args)
    {
        //Constructor =>> yapıcı,oluşturucu,kurucu demektir.
        //Her "Class"ın default bir constructoru vardır. Arzu edersek bu constructoru geliştirebiliriz.
        //ÖRNEK-2 DE GETTER SETTERLAR İLE DAHA KULLANISLI HALE GETİRDİK.
        //ÖNEMLİ !!! =>> "Class" ile "Constructor" ın ismi AYNI olması gerekmektedir. public Product(){.....} diyerek yukarıda Product.java sayfasındaki gibi bir METOT gibi yaklaşabiliriz.
        //ÖRNEK-1 BU ŞEKİL YAZILABİLİR

        Product product = new Product(1,"Laptop","Apple",39000,2,"Black");
        System.out.println(product);



        System.out.println("**************************************************************************************************************");
        //ÖRNEK-2 BU ŞEKİL YAZILABİLİR ikiside aynı şeydir

        //Product product = new Product();
        product.setName("Laptop");
        product.setId(1);
        product.setDescription("Apple");
        product.setPrice(39000);
        product.setStockAmonunt(2);
        product.set_renk("Siyah");


    }

}