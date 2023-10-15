import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        System.out.println("Test Etmek istediğiniz sayıyı Giriniz....");
        Scanner sc = new Scanner(System.in);

        int sayi = Integer.parseInt(sc.nextLine());
        int durum = 1;

        for (int i =2;i<sayi;i++)
        {
            if(sayi%i==0)
            {
                 durum = 0;
                break;
            }
        }
        if(durum == 0)
        {
            System.out.println("Sayı Asal Değildir....");
        }
        else
            System.out.println("Sayımız Asaldır");

    }
}