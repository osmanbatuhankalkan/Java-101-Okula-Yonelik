public class Main {
    public static void main(String[] args)
    {
        // Kendini Tekrar etme prensibi vardır !!!
        // Basit kısa sadece bir işi yapacak şekilde kodunu oluşturmalısın.
        // Javada kodlar MAİN den başlar.
        //MAİN den dallanır proje
        //Bir değişken tanımlandığında tanımlı olduğu kod bloğunda geçerlidir.

        sayiBulmaca();// Methodumuzu çağırdık Classın içinde olduğu için ==> classı main içinde çağırman yeterli
        sayiBulmaca();
        sayiBulmaca();
        sayiBulmaca();
        // Projemizin 4 tane yada bir sürü ekranı var diyelim bunu 4 farklı ekrandan yansıtıyoruz kodu tek tek yazmak zorunda kalmadık 4 ayrı yada daha fazla ekranda yazmaya gerek kalmadan aşşağı tarafta bir classtan çağrıyoruz.
        // Kısaca Fonskiyon yazarak kendimizi tekrar etmenin önüne geçiyoruz.
    }

    //camel casing javada metot yazım adabıdır.
    public  static void  sayiBulmaca()
    {
        int[] sayilar = new int[] {1,2,5,7,9,0};
        int aranacak = 12345;
        boolean varMi = false;

        for (int sayi:sayilar)
        {
            if(sayi==aranacak)
            {
                varMi = true;
                break;
            }
        }
            if(varMi)
            {
                mesajVer(aranacak);
            }else {
                //System.out.println("Sayı mevcut değildir" + aranacak);
                String mesaj;
                mesaj="sayı mevcut değildir: "+" "+aranacak;
                mesajVer2(mesaj);
            }
    }
    //camel casing javada metot yazım adabıdır.
    public  static  void mesajVer(int aranacak)//mesajVerin içine yazdığımız "int aranacak" parametreli metot olmus oldu nornakde assağıda aranacak değişkeni çalışmayacaktı o kod bloğunda tanımlı olmadığı için fakat şimdi metotumuzun içine bu parametreyi ekledik.
    {
        //Bir değişken tanımlandığında tanımlı olduğu kod bloğunda geçerlidir.
        System.out.println("Sayı Mevcuttur: "+ aranacak);
    }
    public static void mesajVer2(String mesaj)
    {
        System.out.printf(mesaj+" "+"Deneme"+"Buradan sözcuk ekleme çıkarma yapabiliyorum ");
    }
}