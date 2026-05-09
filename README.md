# Tugas 5 Pemrograman Mobile - Retrofit & RecyclerView


* **Nama:** Naufal Ihsanul Islam
* **NIM:** F1D02310084
* **Kelas:** C

---

## Hasil Aplikasi (Screenshots)

Berikut adalah tampilan aplikasi saat dijalankan:

| Halaman Login | Halaman Daftar Pasien (RecyclerView) |
|:---:|:---:|
| <img src="Output/Login.png" width="300" alt="Halaman Login"/> | <img src="Output/Hasil%20data.png" width="300" alt="Daftar Data Pasien"/> |

---

## Checklist Kriteria Penilaian

Sesuai ketentuan tugas pada folder `Kriteria tugas`, berikut kriteria yang telah diimplementasikan:

| No | Kriteria Penilaian | Indikator | Status |
|:---:|:---|:---|:---:|
| 1 | **Login API** | Aplikasi berhasil login ke endpoint `/api/login` | **Selesai** |
| 2 | **Token Auth** | Token login dikirim pada header `Authorization: Bearer {token}` saat mengambil data pasien | **Selesai** |
| 3 | **RecyclerView** | Data pasien tampil dalam list (bukan cuma Toast atau Logcat) | **Selesai** |
| 4 | **Model Data** | Data class sesuai dengan struktur JSON response pasien | **Selesai** |
| 5 | **UI dan UX** | Tampilan login dan list pasien rapi, memiliki loading/error state | **Selesai** |
| 6 | **Repository** | Source code & screenshot lengkap tersedia di GitHub | **Selesai** |

---

## Detail Fitur & Alur Kerja

* **Halaman Login**: Validasi input kosong (email & password wajib diisi), indikator progress bar saat request, dan error toast jika login gagal.
* **Penyimpanan Token**: Token hasil login (`data.token`) dan nama user (`data.user.name`) dikirim via intent ke halaman pasien.
* **Request Data Pasien**: Mengirim HTTP GET request ke `/api/pasien` dengan header `Authorization` menggunakan format `Bearer {token}`.
* **Tampilan Pasien**: Nama user ditampilkan di header penyambutan, dan data pasien (Nama, Tanggal Lahir, Jenis Kelamin, Alamat, No. Telepon) diikat ke `item_pasien.xml` menggunakan RecyclerView Adapter.

---

## Endpoint API

* **Login (POST)**: `https://api.pahrul.my.id/api/login`
  * Body: `email` & `password`
* **Daftar Pasien (GET)**: `https://api.pahrul.my.id/api/pasien`
  * Header: `Authorization: Bearer <token>`

---

## Struktur File Penting

* **`Models.kt`**: Berisi data class untuk mapping JSON response API.
* **`RetrofitClient.kt`**: Inisialisasi Retrofit client dan logging interceptor.
* **`ApiService.kt`**: Interface endpoint HTTP POST & GET.
* **`LoginActivity.kt`**: Logika login, validasi input, dan parsing token.
* **`PasienActivity.kt`**: Mengambil data pasien dari API dan memasangnya ke adapter.
* **`PasienAdapter.kt`**: Mengikat list objek `Pasien` ke komponen view di RecyclerView.

---

## Tech Stack

* **Language**: Kotlin
* **Libraries**: Retrofit 2, OkHttp 3, Gson Converter
* **Architecture/UI**: View Binding, RecyclerView, CardView, Coroutines (`lifecycleScope`)