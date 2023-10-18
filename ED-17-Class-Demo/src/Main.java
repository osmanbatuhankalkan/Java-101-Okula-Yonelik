public class Main
{
    public static void main(String[] args)
    {
        //Kullanabilmek için "dörtişlem" classının referansı lazım bize

        DortIslem dortIslem = new DortIslem(); //yarattık
        int sonuc = dortIslem.Topla(7,13);
        System.out.println(sonuc);

        //Classların en büyük özelleği ortak operasyonları tutmaktı.

        int sonuc2 = dortIslem.Cikar(7,13);
        System.out.println(sonuc2);

        int sonuc3 = dortIslem.Carp(7,13);
        System.out.println(sonuc3);

        double sonuc4 =dortIslem.Bol(7,13);
        System.out.println(sonuc4);

    }
}
