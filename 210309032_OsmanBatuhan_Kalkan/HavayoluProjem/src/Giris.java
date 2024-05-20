import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Giris extends JFrame {
    private JTextField emailField;
    private JPasswordField sifreField;
    private AnasayfaUI anasayfaUI;  // Anasayfa referansı

    public Giris() {
        setTitle("Giriş");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Arka plan paneli
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon("/Users/osmanbatuhankalkan/Desktop/210309032_OsmanBatuhan_Kalkan/HavayoluProjem/plane.jpg"); // Arka plan resmi
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());

        // İçerik paneli
        JPanel panel = new JPanel(new GridLayout(5, 2, 20, 20)); // 5 satır, 2 sütun, bileşenler arasında 20 piksel boşluk
        panel.setOpaque(false); // Panelin arka planını şeffaf yapın

        // E-posta etiketi ve alanı
        JLabel emailLabel = new JLabel("E-posta:");
        emailLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(emailLabel);
        emailField = new JTextField();
        panel.add(emailField);

        // Şifre etiketi ve alanı
        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(sifreLabel);
        sifreField = new JPasswordField();
        panel.add(sifreField);

        // Giriş Yap butonu
        JButton loginButton = new JButton("Giriş Yap");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
        panel.add(loginButton);

        // Üye Ol butonu
        JButton registerButton = new JButton("Üye Ol");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Kayit kayit = new Kayit();
                kayit.setAnasayfaUI(anasayfaUI);
                kayit.setVisible(true);
            }
        });
        panel.add(registerButton);

        // Geri Dön butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                anasayfaUI.setVisible(true);  // Ana sayfaya dön
                dispose();
            }
        });
        panel.add(backButton);

        backgroundPanel.add(panel, BorderLayout.CENTER);

        add(backgroundPanel);
    }

    public void setAnasayfaUI(AnasayfaUI anasayfaUI) {
        this.anasayfaUI = anasayfaUI;
    }

    private void login() {
        String email = emailField.getText();
        String sifre = new String(sifreField.getPassword());

        try {
            Connection connection = DbHelper.getConnection();
            String query = "SELECT * FROM yolcu WHERE email = ? AND sifre = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, sifre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "Giriş başarılı!");
                String ad = resultSet.getString("ad");
                anasayfaUI.setGirisYapanKullanici(ad);
                dispose();
                anasayfaUI.setVisible(true);  // Ana sayfaya dön
            } else {
                JOptionPane.showMessageDialog(this, "E-posta veya şifre hatalı!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Giris().setVisible(true);
        });
    }
}
