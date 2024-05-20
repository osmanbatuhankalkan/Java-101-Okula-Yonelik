import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbHelper {
    private static final String URL = "jdbc:mysql://localhost:3306/thyDb";
    private static final String USER = "root";
    private static final String PASSWORD = "59184072";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }

    public static List<String> getYurticiSehirler() {
        List<String> sehirler = new ArrayList<>();
        String query = "SELECT il_adi FROM yurtici";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                sehirler.add(resultSet.getString("il_adi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sehirler;
    }
    public static void showErrorMessage(SQLException exception) {
        System.out.println("Error: " + exception.getMessage());
        System.out.println("Error code: " + exception.getErrorCode());
    }
    public static List<String> getYurtdisiSehirler() {
        List<String> sehirler = new ArrayList<>();
        String query = "SELECT sehir_adi FROM sehirler_yurtdisi";
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                sehirler.add(resultSet.getString("sehir_adi"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sehirler;
    }

    public static void saveSelectedFlight(boolean secenek_ic, boolean secenek_dis, String hat_ic, String hat_dis, String secilen_tarih, String yolcu_tip) {
        String query = "INSERT INTO secilen_ucus (secenek_ic, secenek_dis, hat_ic, hat_dis, secilen_tarih, yolcu_tip) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setBoolean(1, secenek_ic);
            preparedStatement.setBoolean(2, secenek_dis);
            preparedStatement.setString(3, hat_ic);
            preparedStatement.setString(4, hat_dis);
            preparedStatement.setString(5, secilen_tarih);
            preparedStatement.setString(6, yolcu_tip);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
