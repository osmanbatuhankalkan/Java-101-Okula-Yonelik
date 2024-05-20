import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Yardim extends JFrame {
    private AnasayfaUI anasayfaUI; // Anasayfa referansı

    public Yardim() {
        setTitle("Yardım");
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
        JPanel contentPanel = new JPanel(new GridLayout(0, 1, 20, 20));
        contentPanel.setOpaque(false);

        // Bilgi etiketi ve alanı
        JLabel phoneLabel = new JLabel("Telefon No: +90 212 123 45 67");
        phoneLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        contentPanel.add(phoneLabel);

        JLabel emailLabel = new JLabel("Destek E-postası: destek@thy.com");
        emailLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        contentPanel.add(emailLabel);

        JLabel addressLabel = new JLabel("<html>Adres: Turkish Airlines Genel Müdürlük,<br>Atatürk Havalimanı, 34149 İstanbul, Türkiye<html>");
        addressLabel.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        contentPanel.add(addressLabel);

        // Sıkça Sorulan Sorular etiketi ve alanı
        JLabel faqTitle = new JLabel("Sıkça Sorulan Sorular");
        faqTitle.setForeground(Color.WHITE); // Yazı rengini beyaz yap
        faqTitle.setFont(new Font("Arial", Font.BOLD, 16));
        contentPanel.add(faqTitle);

        JLabel question1 = new JLabel("<html><b>Soru 1:</b> Uçuş biletimi nasıl değiştirebilirim?</html>");
        question1.setForeground(Color.WHITE);
        contentPanel.add(question1);

        JLabel answer1 = new JLabel("<html>Cevap: Uçuş biletinizi web sitemizden veya müşteri hizmetlerimizi arayarak değiştirebilirsiniz.</html>");
        answer1.setForeground(Color.WHITE);
        contentPanel.add(answer1);

        JLabel question2 = new JLabel("<html><b>Soru 2:</b> Bagaj hakkım nedir?</html>");
        question2.setForeground(Color.WHITE);
        contentPanel.add(question2);

        JLabel answer2 = new JLabel("<html>Cevap: Ekonomi sınıfında 20 kg, Business sınıfında 30 kg bagaj hakkınız bulunmaktadır.</html>");
        answer2.setForeground(Color.WHITE);
        contentPanel.add(answer2);

        JLabel question3 = new JLabel("<html><b>Soru 3:</b> Online check-in nasıl yapabilirim?</html>");
        question3.setForeground(Color.WHITE);
        contentPanel.add(question3);

        JLabel answer3 = new JLabel("<html>Cevap: Online check-in, uçuşunuzdan 24 saat önce web sitemizden yapılabilir.</html>");
        answer3.setForeground(Color.WHITE);
        contentPanel.add(answer3);

        // Geri Dön butonu
        JButton backButton = new JButton("Geri Dön");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (anasayfaUI != null) {
                    anasayfaUI.setVisible(true); // Ana sayfaya dön
                }
                dispose();
            }
        });

        // Buton paneli
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setOpaque(false); // Arka planı şeffaf yapın
        buttonPanel.add(backButton);

        contentPanel.add(buttonPanel);

        backgroundPanel.add(contentPanel, BorderLayout.CENTER);
        add(backgroundPanel);
    }

    public void setAnasayfaUI(AnasayfaUI anasayfaUI) {
        this.anasayfaUI = anasayfaUI;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Yardim yardim = new Yardim();
            yardim.setVisible(true);
        });
    }
}
