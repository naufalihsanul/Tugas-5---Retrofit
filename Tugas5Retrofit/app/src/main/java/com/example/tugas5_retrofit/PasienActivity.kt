package com.example.tugas5_retrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tugas5_retrofit.databinding.ActivityPasienBinding
import kotlinx.coroutines.launch

class PasienActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasienBinding
    private lateinit var adapter: PasienAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasienBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ambil data dari Intent (dikirim dari LoginActivity)
        val token = intent.getStringExtra("TOKEN") ?: ""
        val userName = intent.getStringExtra("USER_NAME") ?: "User"

        // Tampilkan nama user di bagian atas
        binding.tvWelcome.text = "Selamat Datang, $userName"

        // Setup RecyclerView
        binding.rvPasien.layoutManager = LinearLayoutManager(this)

        // Ambil data pasien dari API
        fetchDataPasien(token)
    }

    private fun fetchDataPasien(token: String) {
        showLoading(true)
        lifecycleScope.launch {
            try {
                // Gunakan format "Bearer {token}" untuk header Authorization
                val response = RetrofitClient.instance.getPasien("Bearer $token")

                if (response.isSuccessful && response.body()?.success == true) {
                    val listPasien = response.body()?.data ?: emptyList()

                    // Pasang data ke adapter
                    adapter = PasienAdapter(listPasien)
                    binding.rvPasien.adapter = adapter

                    if (listPasien.isEmpty()) {
                        Toast.makeText(this@PasienActivity, "Data pasien kosong", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@PasienActivity, "Gagal mengambil data: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@PasienActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}