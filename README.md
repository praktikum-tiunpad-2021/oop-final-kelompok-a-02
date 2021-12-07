# Fifteen Puzzle

This repository is a final project (Java GUI) from Object-Oriented Programming Class, Teknik Informatika Universitas Padjadjaran. 

[Challenge Guidelines](challenge-guideline.md)

Projek membuat game Fifteen Puzzle menggunakan OOP Java

## Credits
| NPM           | Name        |
| ------------- |-------------|
| 140810200017  | M Keenan    |
| 140810200029  | Adnan R M   |
|               |             |

## Change log
- **[Sprint Planning](changelog/sprint-planning.md) - (20 November)** 
   - Eksplor tentang JavaFX untuk game
   - Eksplor tentang game fifteen puzzle

- **[Sprint 1](changelog/sprint-1.md) - (21 - 23 November)** 
   - Pembuatan board dengan tampilan placeholder

- **[Sprint 2](changelog/sprint-2.md) - (27 - 29 November)** 
   - Implementasi Shuffle
   - Implementasi Pergerakan Tile
   
- **[Sprint 3](changelog/sprint-3.md) - (3 - 6 November)** 
   - Implementasi Animasi Pergerakan Tile
   - Perubahan UI (menambah button-button)
   - Penambahan Class GameController
   - Penambahan UML diagram

## Running The App

1. Buka Command Prompt dalam Root Folder Project

2.1. Menjalankan game dengan ukuran 4x4 (default)
``` 
gradlew run
```
2.2. Menjalankan game dengan ukuran custom (contoh : 10x10)(minimal : 2)
``` 
gradlew run --args='10'
```
3.3 Memainkan game
``` 
Click tile untuk menggarakan tile
```
``` 
Tekan R untuk melakukan shuffle
```
``` 
Popup akan muncuk jika puzzle sudah terurut
```

## Classes Used

``` 
App.java
```
Class yang memiliki fungsi main() yang digunakan dalam proses run
- Variables (3)
   - **size** - ukuran board puzzle (n x n)
   - **width** - panjang window game
   - **height** - lebar window game
- Methods (2)
   - **main** - inisiasi **size** berdasarkan argumen launch
   - **start** - inisiasi board, stage dan GameController
``` 
Board.java
```
Class yang merupakan board untuk puzzle, berisi tiles, dan konfigurasi-konfigurasi nya
- Variables (8)
   - **clickHandler** - handler untuk click mouse pada tile
   - **size** - ukuran board puzzle (n x n)
   - **gap** - besar gap antar tile
   - **pixelSizeX** - panjang board dalam pixel
   - **pixelSizeY** - lebar board dalam pixel
   - **generationTileMap** - tile map berupa array dua dimensi yang digunakan untuk shuffle
   - **correctTileMap** - tile map yang digunakan untuk mengecek posisi benar setiap tile
   - **movableIndexes** - index-index tile yang dapat digerakkan
- Methods (12)
   - **toX** - mengubah index tile ke posisi x tile
   - **toY** - mengubah index tile ke posisi y tile
   - **toIndex** - mengubah posisi tile ke index tile
   - **Board** - constructor untuk Board, membuat tile-tile yang diperlukan dengan urutan **correctTileMap** dan mengassign **clickHandler** ke masing-masingnya
   - **getZeroTile** - return tile kosong, berguna untuk menentukan **movableIndexes**
   - **setMovableIndexes** - meng-set **movableIndexes** berdasarkan posisi tile kosong
   - **generateTiles** - mengisi ulang **generationTileMap** dengan urutan yang baru yang solvable
   - **shuffle** - memindahkan semua tile berdasarkan **generationTileMap** baru
   - **isInPlace** - return apakan posisi tile sudah sesuai dengan **correctTileMap**
   - **getInverseCount** - return jumlah inverse pada **generationTileMap**
   - **isSolvable** - return apakah **generationTileMap** solvable
   - **isSolved** - return apkan semua tile sudah sesuai dengan **correctTileMap**
``` 
Tile.java
```
Class yang merupakan tile untuk puzzle dan konfigurasi-konfigurasi nya
- Variables (16)
   - **pixelSizeX** - panjang tile dalam pixel
   - **pixelSizeY** - lebar tile dalam pixel
   - **textColor1** - warna text saat posisi sesuai
   - **textColor0** - warna text saat posisi tidak sesuai
   - **textFont** - font family text
   - **textSize** - ukuran text skalar
   - **pixelTextSize** - ukuran text dalam pixel
   - **textWeight** - tebal text
   - **color0** - warna tile 
   - **color1** - warna tile lainnya
   - **transitionDuration** - durasi animasi pergerakan
   - **transition** - transisi pergerakan yang digunakan
   - **number** - angka pada masing-masing Tile
   - **posX** - posisi X tile dalam board
   - **posY** - posisi Y tile dalam board
   - **board** - referensi ke board yang dipakai
- Methods (10)
   - **resize** - set ukuran tile berdasarkan konfigurasi
   - **init** - inisialisasi tile
   - **Tile** - constructor tile, membuat rectangle dan text dalam tile
   - **createInvisibleTile** - membuat tile kosong
   - **isMovable** - return apakah tile dapat digerakkan
   - **getPosX** - return posisi X tile dalam board
   - **getPosY** - return posisi Y tile dalam board
   - **getIndex** - return index tile dalam board
   - **getNumber** - return angka tile
   - **moveTo** - pindah kan tile ke posisi dalam board yang ditentukan
``` 
GameController.java
```
Class yang merupakan controller untuk mengkontrol game atau board puzzle
- Variables (4)
   - **board** - referensi ke board yang digunakan
   - **scene** - referensi ke scene yang digunakan
   - **stage** - stage yang digunakan oleh controller untuk dialog
   - **pause** - state pause game
- Method (3)
   - **set** - inisiasi GameController
   - **showWonDialog** - muculkan dialog saat game selesai atau menang
   - **isPaused** - return state pause game
``` 
DialogController.java
```
Class yang merupakan controller untuk mengkontrol game atau board puzzle
- Variables (2)
   - **closeBtn** - tombol close
   - **shuffleBtn** - tombol untuk close lalu shuffle
- Method (2)
   - **handleCloseBtn** - fungsi untuk handle **closeBtn**
   - **handleShuffleBtn** - fungsi untuk handle **shuffleBtn**


![image](https://user-images.githubusercontent.com/57803800/144958005-8a31398c-e91b-4c19-9fb7-aa6bc67da144.png)

## Notable Assumption and Design App Details

TO;DO
