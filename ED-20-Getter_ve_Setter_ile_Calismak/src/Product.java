public class Product
{
    private int _id;
     public String _name;
     private String _description;
     private double _price;
     private int _stockAmonunt;
     private String _renk;
     private String _kod;

    //şimdi getterımızı public yazacapız cunku ona ulasmak istiyoruz.
    //Getter
    public int getId()//Bu halde ıd yi okuyabilir fakat yazamaz
    {
        return _id;
    }
    public void setId(int id)//bunu gette vermemiştik çünkü get okuma yapıyordu.Fakat burada biz kullanıcıdan alınana değeri yazacapımız içiz bı değeri tanımlamak gerekmektedir.
    {
        _id =id;//this benim bulunduğum {} içinde geçerli demek. Bizim ilk tanımaldığımız id ile buradaki id farklıdır dikkat.

    }


    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public double get_price() {
        return _price;
    }

    public void set_price(double _price) {
        this._price = _price;
    }

    public int get_stockAmonunt() {
        return _stockAmonunt;
    }

    public void set_stockAmonunt(int _stockAmonunt) {
        this._stockAmonunt = _stockAmonunt;
    }

    public String get_renk() {
        return _renk;
    }

    public void set_renk(String _renk) {
        this._renk = _renk;
    }

    public String get_kod() {
        return this._name.substring(0,3)+_id;
    }

}
