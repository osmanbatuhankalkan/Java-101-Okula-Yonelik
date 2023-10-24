public class Main
{
    public static void main(String[] args)
    {
        //OgretmenKrediManager ogretmenKrediManager = new OgretmenKrediManager();
        //ogretmenKrediManager.Hesapla();
        KrediUI krediUI = new KrediUI();
        krediUI.KrediHesapla(new OgretmenKrediManager());
        //tüm kredi managerlar için ayrı ayrı yapmaktansa, sonrasında da eklenebilir cünkü asker,emekli vs gibi
        //onun için KrediUI da basekredimanager kullanabiliriz
    }
}