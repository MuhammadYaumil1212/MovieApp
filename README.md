# Movie App

Aplikasi Android yang menampilkan daftar film populer dan terbaru dari **The Movie Database (TMDB) API**. Project ini dibangun dengan fokus pada performa dan kode yang bersih (*Clean Code*).

---

## Fitur Utama
* **Explore Movies:** Menampilkan kategori *Now Playing*, *Popular*, dan *Top Rated*.
* **Search Function:** Pencarian film berdasarkan judul secara instan.
* **Detail Information:** Informasi lengkap mulai dari sinopsis, rating, rilis, hingga genre.
* **Caching:** Mendukung penyimpanan data lokal (opsional) untuk akses offline.

---

## Stack Teknologi
* **Language:** Kotlin / Java (Android Native)
* **Architecture:** MVVM (Model-View-ViewModel)
* **Networking:** Retrofit & OkHttp
* **Image Loading:** Glide / Coil
* **Dependency Injection:** Hilt / Koin
* **Local Database:** Room Persistence

## Layer Arsitektur

### 1. Data Layer
Bertanggung jawab untuk berinteraksi dengan sumber data eksternal (API TMDB) dan mengelola detail implementasi teknis.

* **`data/model/`**: Berisi **Data Transfer Objects (DTO) yang dinamakan sebagai Model** seperti `MovieModel` dan `ResultModel` yang menggunakan anotasi `@Serializable` untuk keperluan parsing JSON melalui Ktor.
* **`data/remote/`**: Berisi kontrak API (`MovieApiServices`) dan implementasinya (`MovieApiServicesImpl`) yang menangani *network request*.
* **`data/repository/`**: Implementasi dari repositori (`MovieRepositoryImpl`). Di sini terjadi proses pengambilan data serta mapping dari objek data model ke objek domain entity.

### 2. Domain Layer
Berisi logika bisnis. l ini tidak bergantung pada library Ktor atau Hilt agar tetap bersih.

* **`domain/entity/`**: Berisi objek data murni aplikasi seperti `MovieEntity` dan `MovieGeneralEntity` yang digunakan oleh UI.
* **`domain/repository/`**: Berisi **Interface** `MovieRepository`. Interface ini mendefinisikan kontrak fungsi yang harus diimplementasikan oleh Data Layer.

### 3. UI Layer
Layer untuk menangani tampilan dan interaksi pengguna menggunakan Jetpack Compose.

* **`ui/home/viewmodel/`**: Berisi `HomeViewModel` yang mengelola lifecycle data dan mengubah hasil repository menjadi state yang dapat dibaca UI.
* **`ui/home/state/`**: Berisi `HomeState`, untuk menampung status UI seperti `isLoading`, `movieList`, dan `errorMessage`.
* **`ui/home/screens/`**: Berisi Composable untuk `HomeScreen.kt`.
* **`ui/home/composables/`**: Berisi potongan komponen UI yang dapat digunakan kembali, seperti `MovieRecommendationItem` dan `MovieRecentItem`.

### 4. Core Layer
Menyediakan fungsionalitas dasar yang digunakan di seluruh lapisan aplikasi.

* **`core/constants/`**: Menyimpan nilai konstan yang digunakan di aplikasi.
* **`core/state/`**: Berisi pembungkus data global seperti `sealed class Resources<T>`.
* **`core/utils/`**: Berisi fungsi untuk mapping data `toEntity()`.

---

## 🛠️ Dependency Injection (DI)
* **`di/AppModule`**: Berisi konfigurasi **Hilt** untuk menyediakan *dependencies* seperti `HttpClient` Ktor, API Services, dan Repositori ke seluruh layer dengan otomatis.

---

## Konfigurasi API (Keamanan)

Untuk menjalankan aplikasi ini, Anda perlu mendaftarkan API Key di [TMDB](https://www.themoviedb.org/). Demi keamanan, jangan pernah memasukkan API Key langsung ke dalam kode sumber (Hardcoded).

### Setup `local.properties`
1. Buka file `local.properties` di root project Anda.
2. Tambahkan variabel `API_KEY, BASE URL , dan lain-lain` seperti contoh di bawah ini:

```properties
![WhatsApp Image 2026-02-20 at 01 59 16](https://github.com/user-attachments/assets/23e26d17-708d-4fa1-b1b8-05ab7d8a54c3)

