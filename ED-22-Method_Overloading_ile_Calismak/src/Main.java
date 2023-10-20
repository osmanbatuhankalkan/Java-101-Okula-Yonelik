public class Main {
    public static void main(String[] args)
    {
        DortIslem dortIslem = new DortIslem();
        System.out.println(dortIslem.topla(2,3));
        //yukarıda 2 değişken girilen metotla, burada aşşağıda 3 değişken metot ismi aynıdır bu olaya metotOverloading deniyor.
        dortIslem.topla(2,3,5);
        System.out.println(dortIslem.topla(2,3,5));
    }
}
//DortIslem.Java sayfası içerisindeki kod blogu

//public class DortIslem
//{
//    public int topla(int sayi1, int sayi2)
//    {
//        return sayi1+sayi2;
//    }
//    //aynı isimle 3 tane değişken alabilen bir metot yazdık.
//    public int topla(int sayi1, int sayi2,int sayi3)
//    {
//        return sayi1+sayi2+sayi3;
//    }
//}
