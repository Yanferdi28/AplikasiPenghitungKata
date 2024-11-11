# Tugas 5: Aplikasi Penghitung Kata

### Pembuat
- **Nama**: Ferdhyan Dwi Rangga Saputra
- **NPM**: 2210010171

---

## 1. Deskripsi Program
Aplikasi ini memungkinkan pengguna untuk:
- Memasukkan teks ke dalam **JTextArea** yang ada di dalam **JScrollPane**.
- Menekan tombol **Hitung** untuk menampilkan jumlah kata dan karakter dalam teks yang dimasukkan.
- Menampilkan hasil perhitungan dalam beberapa **JLabel**.

## 2. Komponen GUI
- **JFrame**: Window utama aplikasi.
- **JPanel**: Panel untuk menampung komponen.
- **JLabel**: Label untuk menunjukkan jumlah kata, karakter, kalimat, dan paragraf.
- **JTextArea**: Area input untuk memasukkan teks.
- **JScrollPane**: Panel scroll untuk **JTextArea**.
- **JButton**: Tombol untuk memulai perhitungan dan fungsi lainnya.

## 3. Logika Program
- Menggunakan manipulasi string dan ekspresi reguler untuk menghitung jumlah kata, karakter, kalimat, dan paragraf.
- Menghitung kemunculan kata tertentu dalam teks saat tombol **Cari Kata** ditekan.

## 4. Events
Menggunakan **ActionListener** dan **DocumentListener** untuk interaksi pengguna secara real-time:

### A. Tombol Hitung
Menghitung jumlah kata, karakter, kalimat, dan paragraf dalam teks yang dimasukkan.

```java
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
```

### B. DocumentListener pada JTextArea
Menghitung jumlah kata dan karakter secara real-time setiap kali ada perubahan di **JTextArea**.

```java
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
```
    
## 5. Variasi
Aplikasi ini memiliki variasi tambahan berikut:

### A. Menghitung Jumlah Kalimat dan Paragraf
Menghitung jumlah kalimat dan paragraf dalam teks dengan menggunakan ekspresi reguler.

```java
// Menghitung jumlah kalimat
        String[] kalimat = text.trim().split("[.!?]");
        int jumlahKalimat = text.trim().isEmpty() ? 0 : kalimat.length;

// Menghitung jumlah paragraf
        String[] paragraf = text.trim().split("\\r?\\n");
        int jumlahParagraf = text.trim().isEmpty() ? 0 : paragraf.length;

        lblKalimat.setText("Jumlah Kalimat: " + jumlahKalimat);
        lblParagraf.setText("Jumlah Paragraf: " + jumlahParagraf);
```
        
### B. Pencarian Kata
Memungkinkan pengguna mencari kemunculan kata tertentu dalam teks.

```java
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
```

### C. Menyimpan Hasil ke File
Menyediakan opsi untuk menyimpan teks dan hasil perhitungan ke dalam file menggunakan **JFileChooser**.

```java
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
```


## 6. Tampilan Saat Aplikasi di Run

![image](https://github.com/user-attachments/assets/56dd4b8c-de9a-4818-8158-c7ef1d43be84)

## 7. Indikator Penilaian

| No  | Komponen          | Persentase |
| :-: | ------------------ | :--------: |
|  1  | Komponen GUI      |     10%    |
|  2  | Logika Program    |     20%    |
|  3  | Events            |     10%    |
|  4  | Kesesuaian UI     |     20%    |
|  5  | Memenuhi Variasi  |     40%    |
|     | **TOTAL**         |  **100%**  |

--- 
