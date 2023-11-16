public class Main {
    public static void main(String[] args)
    {
        //String mesaj = "Vade Oranı";

        /*Product product1 = new Product();//Bir class böyle tanımlanır
                        //SET VALUE
        product1.name = "Delongi Kahve Makinesi";
        product1.unitPrice = 7500;
        product1.discount = 7;
        product1.unitInStock = 3;
        product1.imageUrl = "bilmemne.jpg";
                        //GET
        System.out.println(product1.name);
        System.out.println(product1.discount);
        System.out.println(product1.unitPrice);
        System.out.println(product1.unitInStock);
        System.out.println(product1.imageUrl);

                        //Product2
        Product product2 = new Product();
        product2.name = "Arçelik Kahve Makinesi";
        product2.unitPrice = 9500;
        product2.discount = 8;
        product2.unitInStock = 2;
        product2.imageUrl = "bilmemne2.jpg";

                        //Product3
        Product product3 = new Product();
        product3.name = "Vestel Kahve Makinesi";
        product3.unitPrice = 3500;
        product3.discount = 9;
        product3.unitInStock = 6;
        product3.imageUrl = "bilmemne3.jpg";
        //Gerçek hayatta bu bilgiler dB den gelir.

        Product[] products ={product1,product2,product3};
        for (Product product:products)
        {
            System.out.println(product.name);
        } */
        //*********************************************************************************************************

                            //Bundan sonra yazarken 'set' kullanacağız
        Product product1 = new Product();

        product1.setName("Delongi Kahve Makinesi");
        product1.setDiscount(7500);
        product1.setUnitPrice(7);
        product1.setUnitInStock(3);
        product1.setImageUrl("bilmemne.jpg");

        Product product2 = new Product();

        product2.setName("Arçelik Kahve Makinesi");
        product2.setDiscount(9500);
        product2.setUnitPrice(8);
        product2.setUnitInStock(2);
        product2.setImageUrl("bilmemne2.jpg");

        Product product3 = new Product();

        product3.setName("Vestel Kahve Makinesi");
        product3.setDiscount(3500);
        product3.setUnitPrice(9);
        product3.setUnitInStock(5);
        product3.setImageUrl("bilmemne3.jpg");

                            //Bundan sonra okurken 'get' kullanacağız

        Product[] products ={product1,product2,product3};
        for (Product product:products)
        {
            System.out.println(product.getName());
        }
        //********************************//********************************//********************************
        IndividualCustomer individualCustomer = new IndividualCustomer();
        individualCustomer.setCustomerNumber("231521");
        individualCustomer.setPhone("054222222222");
        individualCustomer.setId(1);
        individualCustomer.setFirstName(" Osman Batuhan ");
        individualCustomer.setLastName(" Kalkan ");

        CorporateCustomer corporateCustomer = new CorporateCustomer();
        corporateCustomer.setId(2);
        corporateCustomer.setCustomerNumber("878787878787");
        corporateCustomer.setPhone("0511111111111111");
        corporateCustomer.setTextName("4343434343434");
        corporateCustomer.setCompanyName("abcdf");

        //inheritance(kalıtım) "extends"
        Customer[] customers ={individualCustomer,corporateCustomer};//Bunu arrayda alabildik cünkü customer zaten bunların referans değerini extends dediğimiz için tutuyordu

    }
}