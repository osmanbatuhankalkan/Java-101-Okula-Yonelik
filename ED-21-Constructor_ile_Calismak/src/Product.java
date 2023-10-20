import jdk.jshell.spi.SPIResolutionException;

public class Product
{
    private int id;
    private String name;
    private String description;
    private double price;
    private int stockAmount;
    private String renk;

    //ÖRNEK-1 BU ŞEKİL YAZILABİLİR ikiside aynı şeydir
    //ÖNEMLİ !!! =>> "Class" ile "Constructor" ın ismi AYNI olması gerekmektedir. public Product(){.....} diyerek, bir METOT gibi yaklaşabiliriz.

    public Product(int id,String name,String description,double price,int stockAmount,String renk)
    {
        System.out.println("Yapıcı BLok Çalıştı");
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockAmount = stockAmount;
        this.renk = renk;
    }
    //System.out.println("**************************************************************************************************************");
    //ÖRNEK-2 BU ŞEKİL YAZILABİLİR ikiside aynı şeydir

    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id =id;

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockAmonunt() {
        return stockAmount;
    }

    public void setStockAmonunt(int stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getRenk() {
        return renk;
    }

    public void set_renk(String renk) {
        this.renk = renk;
    }
}
