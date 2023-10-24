public class KrediUI
{
    //BaseKrediManageri ana olarak düşünürsek hem tarımın hem de ogretmen KrMang anası olduğu için
        public void KrediHesapla(BaseKrediManager baseKrediManager)//Biz burada ana kısmı verip çocuklardan istedğimiz kısmı yollayabiliriz bunu yapan REFERANS  tip olaması,sen basekredimanager ogr tarımın da referansını tutuabılır.
        {
            baseKrediManager.Hesapla();


        }
}//Bir Proje yaptık ve SQL,MySQL vs vs. veritabanları desteklesin istiyoruz fakat fazla olduğu için switch olaylarına girmek onun içinde yazmak clean koda uymayacağından. Azan önce yapyığımız gibi ınheritance'ın ana Classına enjekte ederiz.
//**Özellikle birbirinin alternatifi olan geçişleri yapmak için IF leri KULLANMAYIN...


//    public void KrediHesapla(OgretmenKrediManager ogretmenKrediManager)
//    {
//        ogretmenKrediManager.Hesapla();
//        // böyle tek tek yazmaktansa base kullan

//    }



