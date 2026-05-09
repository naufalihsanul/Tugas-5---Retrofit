package com.example.tugas5_retrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tugas5_retrofit.databinding.ActivityLoginBinding
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan password wajib diisi", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            performLogin(email, password)
        }
    }

    private fun performLogin(email: String, password: String) {
        showLoading(true)

        lifecycleScope.launch {
            try {
                val response = RetrofitClient.instance.login(LoginRequest(email, password))

                if (response.isSuccessful && response.body()?.success == true) {
                    val loginData = response.body()?.data
                    if (loginData != null) {
                        Toast.makeText(this@LoginActivity, "Login Berhasil!", Toast.LENGTH_SHORT).show()

                        // Pindah ke PasienActivity membawa token dan nama
                        val intent = Intent(this@LoginActivity, PasienActivity::class.java).apply {
                            putExtra("TOKEN", loginData.token)
                            putExtra("USER_NAME", loginData.user.name)
                        }
                        startActivity(intent)
                        finish()
                    }
                } else {
                    Toast.makeText(this@LoginActivity, "Login Gagal: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            } finally {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        binding.btnLogin.isEnabled = !isLoading
    }
}