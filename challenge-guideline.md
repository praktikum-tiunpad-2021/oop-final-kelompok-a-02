# Challenge Guidelines

A. Deskripsi masalah
- Nama main file adalah 'fifteen-puzzle' (Contoh command: java fifteen-puzzle)
- Secara default akan membentuk (4 x 4) - 1 ubin/tile. 
- Dengan menggunakan args (manfaatkan argument String[] args pada method main), tambahkan argumen 'n' sebagai ukuran custom tile (misal 5x5) (Contoh command: java fifteen-puzzle 5).
- Setiap kali program berjalan akan menampilkan ubin-ubin dengan isi nomor unik yang teracak dari angka 1 sampai (nxn)-1.

B. Setelah app berjalan
- Tampilkan grid ubin dengan besar n x n dimana ubin adalah komponen drawing board.
- Acak urutan angka setiap grid.
- Cek apakah urutan tersebut dapat dipecahkan dengan aturan: Setiap angka yang didahului oleh angka yang lebih besar dianggap sebagai inversi, jumlah inversi dari puzzle harus genap.
- Buat listener klik-an mouse. Setiap mouse diklik akan dicek:
    - Koordinat x dan y dimana klik-an tersebut berada
    - Apakah ubin telah terurut, jika sudah maka ubin diacak lagi
    - Jika ubin belum terurut (game belum berakhir) maka pindahkan ubin ke tempat yang kosong jika memungkinkan
- Buat button “Reset” untuk mengacak ulang urutan dari puzzle.

C. Batasan lainnya
- Method yang digunakan dibebaskan asalkan mencakupi fungsi yang diminta dari poin B.
- Jika ada batasan atau fitur tambahan lain, tolong deskripsikan kepada asprak (akan menjadi nilai lebih)

D. Challenge ideas (optional)
- Buat animasi ketika suatu ubin diklik
