import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UcusYonetim extends JFrame {
    private JButton geriDonButton;
    private JButton goruntuleButton;
    private JButton odemeButton;  // Ödeme butonu
    private JTable table;
    private DefaultTableModel tableModel;

    public UcusYonetim() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Uçuş Yönetim");
        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Arka plan resmi ekleme
        JLabel background = new JLabel(new ImageIcon("/Users/osmanbatuhankalkan/Desktop/210309032_OsmanBatuhan_Kalkan/HavayoluProjem/plane.jpg"));
        background.setBounds(0, 0, 1200, 800);
        setContentPane(background);
        getContentPane().setLayout(null);

        geriDonButton = new JButton("Geri Dön");
        geriDonButton.setBounds(150, 720, 100, 30);
        add(geriDonButton);

        goruntuleButton = new JButton("Görüntüle");
        goruntuleButton.setBounds(40, 720, 100, 30);
        add(goruntuleButton);

        odemeButton = new JButton("Ödeme");
        odemeButton.setBounds(370, 720, 100, 30);  // Yeni butonun konumu
        add(odemeButton);

        // JTable ve modelini ekleme
        tableModel = new DefaultTableModel();
        tableModel.addColumn("PNR NO");
        tableModel.addColumn("İç Hatlar Seçenek");
        tableModel.addColumn("Dış Hatlar Seçenek");
        tableModel.addColumn("İç Hatlar");
        tableModel.addColumn("Dış Hatlar");
        tableModel.addColumn("Tarih");
        tableModel.addColumn("Yolcu Tipi");
        tableModel.addColumn("Ad");
        tableModel.addColumn("Soyad");
        tableModel.addColumn("E-posta");
        tableModel.addColumn("Telefon No");
        tableModel.addColumn("Cinsiyet");
        tableModel.addColumn("Doğum Tarihi");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(50, 50, 1100, 650);
        add(scrollPane);

        geriDonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                geriDonButtonActionPerformed(e);
            }
        });

        goruntuleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goruntuleButtonActionPerformed(e);
            }
        });

        odemeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Odeme odeme = new Odeme(UcusYonetim.this); // Doğru parametreyi geçir
                odeme.setVisible(true);
            }
        });
    }

    private void goruntuleButtonActionPerformed(ActionEvent e) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DbHelper.getConnection();
            String query = "SELECT su.*, y.* FROM secilen_ucus su JOIN yolcu y ON su.pnr_No = y.pnr_No";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            tableModel.setRowCount(0);  // Mevcut verileri temizle

            while (resultSet.next()) {
                int pnr_No = resultSet.getInt("pnr_No");
                boolean secenek_ic = resultSet.getBoolean("secenek_ic");
                boolean secenek_dis = resultSet.getBoolean("secenek_dis");
                String hat_ic = resultSet.getString("hat_ic");
                String hat_dis = resultSet.getString("hat_dis");
                String secilen_tarih = resultSet.getString("secilen_tarih");
                String yolcu_tip = resultSet.getString("yolcu_tip");

                // yolcu tablosundan bilgiler
                String ad = resultSet.getString("ad");
                String soyad = resultSet.getString("soyad");
                String email = resultSet.getString("email");
                String telefon_no = resultSet.getString("telefon_no");
                String cinsiyet = resultSet.getString("cinsiyet");
                String dogum_tarihi = resultSet.getString("dogum_tarihi");

                tableModel.addRow(new Object[]{pnr_No, secenek_ic, secenek_dis, hat_ic, hat_dis, secilen_tarih, yolcu_tip, ad, soyad, email, telefon_no, cinsiyet, dogum_tarihi});
            }
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        } finally {
            try {
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            } catch (SQLException exception) {
                DbHelper.showErrorMessage(exception);
            }
        }
    }

    private void geriDonButtonActionPerformed(ActionEvent e) {
        new AnasayfaUI().setVisible(true);
        dispose(); // Bu pencereyi kapat
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UcusYonetim().setVisible(true);
            }
        });
    }
}
