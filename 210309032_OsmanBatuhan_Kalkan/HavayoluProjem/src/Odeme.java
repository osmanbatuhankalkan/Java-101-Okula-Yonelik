import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Odeme extends JFrame {
    private JTextField kartNumarasiField;
    private JTextField ayField;
    private JTextField yilField;
    private JTextField cvvField;
    private UcusYonetim ucusYonetim;  // Ana sayfa referansı

    public Odeme(UcusYonetim ucusYonetim) {
        this.ucusYonetim = ucusYonetim;
        initComponents();
    }

    private void initComponents() {
        setTitle("Ödeme");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel kartNumarasiLabel = new JLabel("Kart Numarası:");
        kartNumarasiField = new JTextField();
        panel.add(kartNumarasiLabel);
        panel.add(kartNumarasiField);

        JLabel ayLabel = new JLabel("Ay:");
        ayField = new JTextField();
        panel.add(ayLabel);
        panel.add(ayField);

        JLabel yilLabel = new JLabel("Yıl:");
        yilField = new JTextField();
        panel.add(yilLabel);
        panel.add(yilField);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField();
        panel.add(cvvLabel);
        panel.add(cvvField);

        JButton odemeButton = new JButton("Ödeme Yap");
        odemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                odemeYap();
            }
        });

        panel.add(new JLabel());  // Boşluk eklemek için
        panel.add(odemeButton);

        add(panel);
    }

    private void odemeYap() {
        String kartNumarasi = kartNumarasiField.getText();
        int ay = Integer.parseInt(ayField.getText());
        int yil = Integer.parseInt(yilField.getText());
        String cvv = cvvField.getText();

        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DbHelper.getConnection();
            String sql = "SELECT * FROM kredi_karti WHERE kart_numarasi = ? AND son_kullanma_ayi = ? AND son_kullanma_yili = ? AND cvv = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, kartNumarasi);
            statement.setInt(2, ay);
            statement.setInt(3, yil);
            statement.setString(4, cvv);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "İşlem başarılı!");
                ucusYonetim.setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Geçersiz kart bilgileri!");
            }
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException exception) {
                DbHelper.showErrorMessage(exception);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                UcusYonetim ucusYonetim = new UcusYonetim();  // UcusYonetim sınıfını başlat
                ucusYonetim.setVisible(true);
                new Odeme(ucusYonetim).setVisible(true);  // Odeme penceresini başlat
            }
        });
    }
}
