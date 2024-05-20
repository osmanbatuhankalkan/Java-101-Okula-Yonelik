import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Kayit extends JFrame {
    private JTextField adField;
    private JTextField soyadField;
    private JPasswordField sifreField;
    private JTextField dogumTarihiField;
    private JRadioButton erkekRadio;
    private JRadioButton kadinRadio;
    private JTextField emailField;
    private JTextField telefonNoField;
    private AnasayfaUI anasayfaUI;  // Anasayfa referansı

    public Kayit() {
        setTitle("Üye Ol");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        JPanel panel = new JPanel(new GridLayout(10, 2, 15, 15)); // 10 satır, 2 sütun, bileşenler arasında 15 piksel boşluk
        panel.setOpaque(false); // Panelin arka planını şeffaf yapın

        // Ad
        JLabel adLabel = new JLabel("Ad:");
        adLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(adLabel);
        adField = new JTextField(20);
        panel.add(adField);

        // Soyad
        JLabel soyadLabel = new JLabel("Soyad:");
        soyadLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(soyadLabel);
        soyadField = new JTextField(20);
        panel.add(soyadField);

        // Şifre
        JLabel sifreLabel = new JLabel("Şifre:");
        sifreLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(sifreLabel);
        sifreField = new JPasswordField(20);
        panel.add(sifreField);

        // Doğum Tarihi
        JLabel dogumTarihiLabel = new JLabel("Doğum Tarihi (YYYY-MM-DD):");
        dogumTarihiLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(dogumTarihiLabel);
        dogumTarihiField = new JTextField(20);
        panel.add(dogumTarihiField);

        // Cinsiyet
        JLabel cinsiyetLabel = new JLabel("Cinsiyet:");
        cinsiyetLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(cinsiyetLabel);
        JPanel cinsiyetPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        cinsiyetPanel.setOpaque(false);
        erkekRadio = new JRadioButton("Erkek");
        erkekRadio.setForeground(Color.WHITE);
        erkekRadio.setOpaque(false);
        kadinRadio = new JRadioButton("Kadın");
        kadinRadio.setForeground(Color.WHITE);
        kadinRadio.setOpaque(false);
        ButtonGroup cinsiyetGroup = new ButtonGroup();
        cinsiyetGroup.add(erkekRadio);
        cinsiyetGroup.add(kadinRadio);
        cinsiyetPanel.add(erkekRadio);
        cinsiyetPanel.add(kadinRadio);
        panel.add(cinsiyetPanel);

        // E-posta
        JLabel emailLabel = new JLabel("E-posta:");
        emailLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(emailLabel);
        emailField = new JTextField(20);
        panel.add(emailField);

        // Telefon No
        JLabel telefonNoLabel = new JLabel("Telefon No:");
        telefonNoLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        panel.add(telefonNoLabel);
        telefonNoField = new JTextField(20);
        panel.add(telefonNoField);

        // Üye Ol butonu
        JButton registerButton = new JButton("Üye Ol");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                register();
            }
        });
        panel.add(registerButton);

        // Geri Dön butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (anasayfaUI != null) {
                    anasayfaUI.setVisible(true); // Ana sayfaya dön
                }
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

    private void register() {
        String ad = adField.getText();
        String soyad = soyadField.getText();
        String sifre = new String(sifreField.getPassword());
        String dogumTarihi = dogumTarihiField.getText();
        String cinsiyet = erkekRadio.isSelected() ? "Erkek" : "Kadın";
        String email = emailField.getText();
        String telefonNo = telefonNoField.getText();

        try {
            Connection connection = DbHelper.getConnection();
            String query = "INSERT INTO yolcu (ad, soyad, sifre, dogum_tarihi, cinsiyet, email, telefon_no) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, ad);
            statement.setString(2, soyad);
            statement.setString(3, sifre);
            statement.setString(4, dogumTarihi);
            statement.setString(5, cinsiyet);
            statement.setString(6, email);
            statement.setString(7, telefonNo);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Kayıt başarılı!");
            dispose();
            anasayfaUI.setVisible(true);  // Ana sayfaya dön
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Kayit().setVisible(true);
        });
    }
}
