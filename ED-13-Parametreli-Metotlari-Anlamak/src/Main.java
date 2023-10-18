public class Main {
    public static void main(String[] args)
    {
        String mesaj = "Bugün hava çok güzel..";

        String yeniMesaj= mesaj.substring(0,2);//demekki substring bir değer döndürüyor
        System.out.println(yeniMesaj);

        guncelle();
        sil();
        ekle();

        String yeniMesaj2 =sehirVer();
        int sayi = topla(5,7);//burada sayı işlemimizi topla fonksiyonumuza yaptırmıs olduk.
        System.out.println(sayi);
    }
    //Void operasyonlar emir kipleri gibidir yap,git,gel,eklee,sil,güncelle gibi
    //illada void olmak zorunda değildir bana eklediğin değeri geri verde voidi değiştireceğiz
    public static void ekle(){
        System.out.println("Eklendi...");
    }
    public static void sil(){
        System.out.println("Silindi....");
    }
    public static void guncelle(){
        System.out.println("Güncellendi....");

    }
    public static int topla(int sayi1,int sayi2)
    {
        //bu değer int türünde değer döndürür demek

        return sayi1+sayi2;//return bitir demektir metotoru devam ettirme. biz return5 diyerek bitir ama 5 döndür diyoruz.
    }
    public static String sehirVer(){//strin değer döndüren metot
       return "Antalya";//sehir ver metotdu string olan antalya değerini döndürüyor.
    }
}