import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

public class AnasayfaUI extends JFrame {
    private JButton girisButton;
    private JButton cikisButton;
    private JLabel kullaniciLabel;
    private JRadioButton icHatlarRadio;
    private JRadioButton disHatlarRadio;
    private JComboBox<String> fromFieldDomestic;
    private JComboBox<String> toFieldDomestic;
    private JComboBox<String> fromFieldInternational;
    private JComboBox<String> toFieldInternational;
    private JTextField dateField;
    private JComboBox<String> passengersComboBox;

    public AnasayfaUI() {
        // Frame ayarları
        setTitle("Turkish Airlines");
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

        // Üst panel (logo ve menüler)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(51, 51, 51)); // Arka plan rengini ayarlayın
        topPanel.setPreferredSize(new Dimension(1200, 80)); // Top panel boyutunu belirleyin

        // Logo
        JLabel logoLabel = new JLabel(new ImageIcon("turkish-airlines.png"));
        logoLabel.setPreferredSize(new Dimension(300, 80)); // Logo boyutunu belirleyin
        topPanel.add(logoLabel, BorderLayout.WEST);

        // Menü paneli
        JPanel menuPanel = new JPanel(new FlowLayout());
        menuPanel.setOpaque(false);

        JButton planlaUçButton = new JButton("PLANLA & UÇ");
        menuPanel.add(planlaUçButton);

        JButton yardimButton = new JButton("YARDIM");
        yardimButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Yardim yardim = new Yardim();
                yardim.setAnasayfaUI(AnasayfaUI.this);
                yardim.setVisible(true);
                setVisible(false); // AnasayfaUI penceresini gizle
            }
        });
        menuPanel.add(yardimButton);

        // Üye Ol butonu
        JButton registerButton = new JButton("ÜYE OL");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Kayit kayit = new Kayit();
                kayit.setAnasayfaUI(AnasayfaUI.this);
                kayit.setVisible(true);
            }
        });
        menuPanel.add(registerButton);

        // Giriş Yap butonu
        girisButton = new JButton("GİRİŞ YAP");
        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Giris giris = new Giris();
                giris.setAnasayfaUI(AnasayfaUI.this);
                giris.setVisible(true);
            }
        });
        menuPanel.add(girisButton);

        // Çıkış Yap butonu
        cikisButton = new JButton("ÇIKIŞ YAP");
        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGirisYapanKullanici(null);
            }
        });
        cikisButton.setVisible(false); // Başlangıçta gizli
        menuPanel.add(cikisButton);

        JButton sorguButton = new JButton("E-Sorgu");
        sorguButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new E_Sorgu().setVisible(true);
            }
        });
        menuPanel.add(sorguButton);

        topPanel.add(menuPanel, BorderLayout.CENTER);

        // Kullanıcı adı etiketi
        kullaniciLabel = new JLabel();
        kullaniciLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        kullaniciLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        topPanel.add(kullaniciLabel, BorderLayout.EAST);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);

        // Uçuş arama paneli
        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setOpaque(false); // Arama panelinin arka planını şeffaf yapın
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        ButtonGroup hatTypeGroup = new ButtonGroup();
        icHatlarRadio = new JRadioButton("İç Hatlar");
        icHatlarRadio.setForeground(Color.WHITE);
        icHatlarRadio.setOpaque(false);

        disHatlarRadio = new JRadioButton("Dış Hatlar");
        disHatlarRadio.setForeground(Color.WHITE);
        disHatlarRadio.setOpaque(false);

        hatTypeGroup.add(icHatlarRadio);
        hatTypeGroup.add(disHatlarRadio);

        icHatlarRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromFieldInternational.setEnabled(false);
                toFieldInternational.setEnabled(false);
                fromFieldDomestic.setEnabled(true);
                toFieldDomestic.setEnabled(true);
            }
        });

        disHatlarRadio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fromFieldInternational.setEnabled(true);
                toFieldInternational.setEnabled(true);
                fromFieldDomestic.setEnabled(false);
                toFieldDomestic.setEnabled(false);
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        searchPanel.add(icHatlarRadio, gbc);
        gbc.gridx = 1;
        searchPanel.add(disHatlarRadio, gbc);

        JLabel fromLabelDomestic = new JLabel("İç Hatlar Nereden");
        fromLabelDomestic.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        searchPanel.add(fromLabelDomestic, gbc);

        fromFieldDomestic = new JComboBox<>(DbHelper.getYurticiSehirler().toArray(new String[0]));
        fromFieldDomestic.setPreferredSize(new Dimension(200, 30));
        fromFieldDomestic.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 1;
        searchPanel.add(fromFieldDomestic, gbc);

        JLabel toLabelDomestic = new JLabel("İç Hatlar Nereye");
        toLabelDomestic.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        searchPanel.add(toLabelDomestic, gbc);

        toFieldDomestic = new JComboBox<>(DbHelper.getYurticiSehirler().toArray(new String[0]));
        toFieldDomestic.setPreferredSize(new Dimension(200, 30));
        toFieldDomestic.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        searchPanel.add(toFieldDomestic, gbc);

        JLabel fromLabelInternational = new JLabel("Dış Hatlar Nereden");
        fromLabelInternational.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        searchPanel.add(fromLabelInternational, gbc);

        fromFieldInternational = new JComboBox<>(DbHelper.getYurtdisiSehirler().toArray(new String[0]));
        fromFieldInternational.setPreferredSize(new Dimension(200, 30));
        fromFieldInternational.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        searchPanel.add(fromFieldInternational, gbc);

        JLabel toLabelInternational = new JLabel("Dış Hatlar Nereye");
        toLabelInternational.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        searchPanel.add(toLabelInternational, gbc);

        toFieldInternational = new JComboBox<>(DbHelper.getYurtdisiSehirler().toArray(new String[0]));
        toFieldInternational.setPreferredSize(new Dimension(200, 30));
        toFieldInternational.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        searchPanel.add(toFieldInternational, gbc);

        JLabel dateLabel = new JLabel("Tarih");
        dateLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 5;
        searchPanel.add(dateLabel, gbc);

        dateField = new JTextField(20);
        dateField.setPreferredSize(new Dimension(200, 30));
        dateField.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 5;
        searchPanel.add(dateField, gbc);

        JLabel passengersLabel = new JLabel("Yolcular");
        passengersLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 6;
        searchPanel.add(passengersLabel, gbc);

        String[] yolcuTipleri = {"Öğrenci", "Memur", "İş insanı", "Özel Sektör", "Diğer"};
        passengersComboBox = new JComboBox<>(yolcuTipleri);
        passengersComboBox.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 6;
        searchPanel.add(passengersComboBox, gbc);

        JButton searchButton = new JButton("Uçuş Ara");
        searchButton.setPreferredSize(new Dimension(200, 30));
        searchButton.setOpaque(false);
        gbc.gridx = 1;
        gbc.gridy = 7;
        searchPanel.add(searchButton, gbc);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int pnrNo = generateRandomNumber();
                if (saveSelectedFlight(pnrNo) && updateYolcuWithPnr(pnrNo)) {
                    new UcusYonetim().setVisible(true);
                    setVisible(false);
                }
            }
        });

        backgroundPanel.add(searchPanel, BorderLayout.CENTER);
        add(backgroundPanel);
    }

    public void setGirisYapanKullanici(String ad) {
        if (ad != null) {
            kullaniciLabel.setText("Hoşgeldiniz, " + ad);
            girisButton.setVisible(false);
            cikisButton.setVisible(true);
        } else {
            kullaniciLabel.setText("");
            girisButton.setVisible(true);
            cikisButton.setVisible(false);
        }
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(1000000); // 0 ile 999999 arasında rastgele bir sayı üret
    }

    private boolean saveSelectedFlight(int pnrNo) {
        boolean secenek_ic = icHatlarRadio.isSelected();
        boolean secenek_dis = disHatlarRadio.isSelected();
        String hat_ic = secenek_ic ? fromFieldDomestic.getSelectedItem().toString() + " - " + toFieldDomestic.getSelectedItem().toString() : "";
        String hat_dis = secenek_dis ? fromFieldInternational.getSelectedItem().toString() + " - " + toFieldInternational.getSelectedItem().toString() : "";
        String secilen_tarih = dateField.getText();
        String yolcu_tip = passengersComboBox.getSelectedItem().toString();

        try {
            Connection connection = DbHelper.getConnection();
            String query = "INSERT INTO secilen_ucus (pnr_No, secenek_ic, secenek_dis, hat_ic, hat_dis, secilen_tarih, yolcu_tip) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pnrNo);
            statement.setBoolean(2, secenek_ic);
            statement.setBoolean(3, secenek_dis);
            statement.setString(4, hat_ic);
            statement.setString(5, hat_dis);
            statement.setString(6, secilen_tarih);
            statement.setString(7, yolcu_tip);
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true; // Kayıt başarılı
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
            return false; // Kayıt başarısız
        }
    }

    private boolean updateYolcuWithPnr(int pnrNo) {
        try {
            Connection connection = DbHelper.getConnection();
            String query = "UPDATE yolcu SET pnr_No = ? WHERE id = ?"; // Kullanıcının id'sini buradan alırız
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, pnrNo);
            statement.setInt(2, getCurrentUserId()); // Şu anki kullanıcının ID'sini al
            statement.executeUpdate();
            statement.close();
            connection.close();
            return true; // Kayıt başarılı
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
            return false; // Kayıt başarısız
        }
    }

    private int getCurrentUserId() {
        // Şu anki kullanıcının ID'sini çekeriz
        return 1; // Örnek olarak, ID'yi 1 olarak döndürüyoruz
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AnasayfaUI ui = new AnasayfaUI();
            ui.setVisible(true);
        });
    }
}
