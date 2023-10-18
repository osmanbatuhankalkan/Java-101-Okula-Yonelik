public class Main {
    public static void main(String[] args) {

        int sayi = topla(5, 7);//burada sayı işlemimizi topla fonksiyonumuza yaptırmıs olduk.
        System.out.println(sayi);

        int toplam = topla2(1, 2, 3, 4, 5, 6, 8, 0);
        System.out.println(toplam);
        //Demiştikki array görevi görür , biz arrayde değlerimiz içierisind gezinebiliyorduk ozaman tek tek gezip toplayan bir sistem yazdık.

    }


    public static int topla(int sayi1,int sayi2)
    {
        //bu değer int türünde değer döndürür demek

        return sayi1+sayi2;//return bitir demektir metotoru devam ettirme. biz return5 diyerek bitir ama 5 döndür diyoruz.
    }
    public static int topla2(int... sayilar)// Birden fazla int değer girip bir işlem yapacaksan ve aded net değilse int... koyarak bunu aşabilirisn bir nevi array görevi görür int array göndermişssin gibi
    {
        int toplam=0;
        for (int sayi:sayilar)//Demiştikki array görevi görür , biz arrayde değlerimiz içierisind gezinebiliyorduk ozaman tek tek gezip toplayan bir sistem yazdık.
        {
            toplam+=sayi;
        }
        return toplam;
    }


}