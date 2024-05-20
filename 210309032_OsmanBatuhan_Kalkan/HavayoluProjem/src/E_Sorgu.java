import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class E_Sorgu extends JFrame {
    private JTextField pnrNoField;
    private JTextArea resultArea;

    public E_Sorgu() {
        setTitle("E-Sorgu");
        setSize(600, 400);
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

        // Üst panel
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.setOpaque(false);

        JLabel pnrNoLabel = new JLabel("PNR No:");
        pnrNoField = new JTextField(20);
        JButton sorgulaButton = new JButton("Sorgula");

        sorgulaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sorgulaButtonActionPerformed(e);
            }
        });

        topPanel.add(pnrNoLabel);
        topPanel.add(pnrNoField);
        topPanel.add(sorgulaButton);

        // Sonuç alanı
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        backgroundPanel.add(topPanel, BorderLayout.NORTH);
        backgroundPanel.add(scrollPane, BorderLayout.CENTER);

        add(backgroundPanel);
    }

    private void sorgulaButtonActionPerformed(ActionEvent e) {
        String pnrNo = pnrNoField.getText().trim();
        if (!pnrNo.isEmpty()) {
            sorgulaPNR(pnrNo);
        } else {
            JOptionPane.showMessageDialog(this, "Lütfen PNR No giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void sorgulaPNR(String pnrNo) {
        try {
            Connection connection = DbHelper.getConnection();
            String query = "SELECT su.*, y.* FROM secilen_ucus su JOIN yolcu y ON su.pnr_No = y.pnr_No WHERE su.pnr_No = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, pnrNo);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder result = new StringBuilder();
            if (resultSet.next()) {
                // secilen_ucus tablosundan bilgiler
                result.append("PNR No: ").append(resultSet.getString("pnr_No")).append("\n");
                result.append("İç Hatlar Seçenek: ").append(resultSet.getBoolean("secenek_ic")).append("\n");
                result.append("Dış Hatlar Seçenek: ").append(resultSet.getBoolean("secenek_dis")).append("\n");
                result.append("İç Hatlar: ").append(resultSet.getString("hat_ic")).append("\n");
                result.append("Dış Hatlar: ").append(resultSet.getString("hat_dis")).append("\n");
                result.append("Tarih: ").append(resultSet.getString("secilen_tarih")).append("\n");
                result.append("Yolcu Tipi: ").append(resultSet.getString("yolcu_tip")).append("\n");

                // yolcu tablosundan bilgiler
                result.append("\nYolcu Bilgileri:\n");
                result.append("Ad: ").append(resultSet.getString("ad")).append("\n");
                result.append("Soyad: ").append(resultSet.getString("soyad")).append("\n");
                result.append("E-posta: ").append(resultSet.getString("email")).append("\n");
                result.append("Telefon No: ").append(resultSet.getString("telefon_no")).append("\n");
                result.append("Cinsiyet: ").append(resultSet.getString("cinsiyet")).append("\n");
                result.append("Doğum Tarihi: ").append(resultSet.getString("dogum_tarihi")).append("\n");
            } else {
                result.append("PNR No bulunamadı.");
            }

            resultArea.setText(result.toString());
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException exception) {
            DbHelper.showErrorMessage(exception);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new E_Sorgu().setVisible(true);
        });
    }
}
