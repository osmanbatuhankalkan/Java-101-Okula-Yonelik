public class Main {
    public static void main(String[] args)
    {
        //Inheritance ==> Miras durumudur. Bir nesne bir diğer nesneye miras bırakır.
        Customer customer = new Customer();
        Employee employee = new Employee();
        //BURADA AŞŞAĞIDA GÖRÜYORUZ BASİT BİR İNHERİTANCE ÖRNEĞİ ORTAK OLAN ÖZELLİKLERİMİZ HER İKİ NESNEMİZ İÇİNDE GELİYOR ÇALIŞIYOR.
//        customer.age(); //Ortak
//        customer.firstName; //Ortak
//        customer.email; //Kendi Sınıfına özel
//        customer.id; //Ortak
//        customer.lastName; //Ortak

//        employee.age(); //Ortak
//        employee.firstName; //Ortak
//        employee.salary; //Kendi Sınıfına özel
//        employee.id(); //Ortak
//        employee.lastName();/ /Ortak

        EmloyeeManager employeeManager = new EmloyeeManager();
        CustomerManager customerManager = new CustomerManager();


    }
}