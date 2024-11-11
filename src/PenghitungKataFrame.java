/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 *
 * @author Fetra
 */
public class PenghitungKataFrame extends javax.swing.JFrame {
    
    private boolean isRealTimeCountingEnabled = false;

    /**
     * Creates new form PenghitungKataFrame
     */
    public PenghitungKataFrame() {
        initComponents();
        hitungActionListener();
        hitungDocumentListener();
    }
    
    // Metode untuk menambahkan ActionListener pada tombol Hitung
    private void hitungActionListener() {
        btnHitung.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitungJumlah(); // Hitung pertama kali ketika tombol diklik
                isRealTimeCountingEnabled = true; // Mengaktifkan DocumentListener untuk perhitungan real-time
            }
        });
        
        // ActionListener untuk tombol Cari
        btnCari.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cariKata(); // Memanggil metode pencarian kata
            }
        });
        
        // ActionListener untuk tombol Simpan
        btnSimpan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                simpanKeFile(); // Memanggil metode penyimpanan ke file
            }
        });
    }

    // Metode untuk menambahkan DocumentListener pada JTextArea
    private void hitungDocumentListener() {
        taHitung.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                if (isRealTimeCountingEnabled) {
                    hitungJumlah();
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                if (isRealTimeCountingEnabled) {
                    hitungJumlah();
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                if (isRealTimeCountingEnabled) {
                    hitungJumlah();
                }
            }
        });
    }

    // Metode untuk menghitung jumlah kata, kalimat, karakter, dan paragraf
    private void hitungJumlah() {
        String text = taHitung.getText();

        // Menghitung jumlah kata
        String[] words = text.trim().split("\\s+");
        int jumlahKata = text.trim().isEmpty() ? 0 : words.length;

        // Menghitung jumlah kalimat
        String[] kalimat = text.trim().split("[.!?]");
        int jumlahKalimat = text.trim().isEmpty() ? 0 : kalimat.length;

        // Menghitung jumlah karakter (termasuk spasi)
        int jumlahKarakter = text.length();

        // Menghitung jumlah paragraf
        String[] paragraf = text.trim().split("\\r?\\n");
        int jumlahParagraf = text.trim().isEmpty() ? 0 : paragraf.length;

        // Menampilkan hasil perhitungan pada label
        lblKata.setText("Jumlah Kata: " + jumlahKata);
        lblKalimat.setText("Jumlah Kalimat: " + jumlahKalimat);
        lblKarakter.setText("Jumlah Karakter: " + jumlahKarakter);
        lblParagraf.setText("Jumlah Paragraf: " + jumlahParagraf);
    }
    
    // Metode untuk mencari kata di JTextArea
    private void cariKata() {
        String text = taHitung.getText();
        String kataCari = txtCari.getText().trim();

        if (kataCari.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Masukkan kata yang ingin dicari.", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int occurrences = 0;
        int index = text.indexOf(kataCari);

        // Hitung berapa kali kata muncul dalam teks
        while (index != -1) {
            occurrences++;
            index = text.indexOf(kataCari, index + kataCari.length());
        }

        // Menampilkan hasil pencarian
        if (occurrences > 0) {
            JOptionPane.showMessageDialog(this, "Kata \"" + kataCari + "\" ditemukan sebanyak " + occurrences + " kali.", "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Kata \"" + kataCari + "\" tidak ditemukan.", "Hasil Pencarian", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Metode untuk menyimpan teks dan hasil perhitungan ke file
    private void simpanKeFile() {
        // Menggunakan JFileChooser untuk memilih lokasi penyimpanan
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Simpan Teks dan Hasil Perhitungan");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            
            try (FileWriter writer = new FileWriter(fileToSave)) {
                // Menulis teks dari JTextArea
                writer.write("Teks:\n");
                writer.write(taHitung.getText() + "\n\n");

                // Menulis hasil perhitungan
                writer.write("Hasil Perhitungan:\n");
                writer.write(lblKata.getText() + "\n");
                writer.write(lblKalimat.getText() + "\n");
                writer.write(lblKarakter.getText() + "\n");
                writer.write(lblParagraf.getText() + "\n");

                writer.flush();
                JOptionPane.showMessageDialog(this, "File berhasil disimpan!", "Simpan Berhasil", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taHitung = new javax.swing.JTextArea();
        txtCari = new javax.swing.JTextField();
        btnHitung = new javax.swing.JButton();
        btnCari = new javax.swing.JButton();
        lblKata = new javax.swing.JLabel();
        lblKalimat = new javax.swing.JLabel();
        lblKarakter = new javax.swing.JLabel();
        lblParagraf = new javax.swing.JLabel();
        btnSimpan = new javax.swing.JButton();

        jButton3.setText("jButton3");

        jLabel5.setText("jLabel5");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Aplikasi Penghitung Kata");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aplikasi Penghitung Kata", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Segoe UI", 1, 24))); // NOI18N
        jPanel1.setLayout(new java.awt.GridBagLayout());

        taHitung.setColumns(20);
        taHitung.setRows(5);
        jScrollPane1.setViewportView(taHitung);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(jScrollPane1, gridBagConstraints);

        txtCari.setPreferredSize(new java.awt.Dimension(232, 84));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(txtCari, gridBagConstraints);

        btnHitung.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnHitung.setText("Hitung Kata");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(btnHitung, gridBagConstraints);

        btnCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCari.setText("Cari Kata");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(btnCari, gridBagConstraints);

        lblKata.setText("Jumlah Kata:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(lblKata, gridBagConstraints);

        lblKalimat.setText("Jumlah Kalimat:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(lblKalimat, gridBagConstraints);

        lblKarakter.setText("Jumlah Karakter:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(lblKarakter, gridBagConstraints);

        lblParagraf.setText("Jumlah Paragraf:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(lblParagraf, gridBagConstraints);

        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSimpan.setText("Simpan");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new java.awt.Insets(4, 4, 4, 4);
        jPanel1.add(btnSimpan, gridBagConstraints);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PenghitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PenghitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PenghitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PenghitungKataFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PenghitungKataFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHitung;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblKalimat;
    private javax.swing.JLabel lblKarakter;
    private javax.swing.JLabel lblKata;
    private javax.swing.JLabel lblParagraf;
    private javax.swing.JTextArea taHitung;
    private javax.swing.JTextField txtCari;
    // End of variables declaration//GEN-END:variables
}
