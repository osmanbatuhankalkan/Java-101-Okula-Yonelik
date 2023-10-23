public class Main {
    public static void main(String[] args)
    {
        //Burada Harflerimizi biz kendimiz manuel verdik, ilerleyen zamanda veri paketinden otomatik olarak gelen verilerin uygunluğunu test eden kodlarımızı uyarlayacağiz.
        char harf ='A';

        //Kontrol olaylarımızda switch - case tercih edebiliriz.
        switch ((harf))
        {
            case 'A':
            case 'I':
            case 'O':
            case 'U':
                System.out.println("Kalın Sesli Harf");
                break;
            default:
                System.out.println("İnce Sesli Harfler");
        }

    }
}