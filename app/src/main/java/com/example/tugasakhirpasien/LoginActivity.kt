package com.example.tugasakhirpasien

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tugasakhirpasien.network.ApiService
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etEmail = findViewById<EditText>(R.id.etEmail)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        val apiService = ApiService.create()

        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            progressBar.visibility = View.VISIBLE
            btnLogin.isEnabled = false

            lifecycleScope.launch {
                try {
                    val loginBody = mapOf("email" to email, "password" to password)
                    val response = apiService.login(loginBody)

                    if (response.isSuccessful && response.body()?.success == true) {
                        val loginData = response.body()?.data
                        val token = loginData?.token
                        val name = loginData?.user?.name

                        val intent = Intent(this@LoginActivity, MainActivity::class.java).apply {
                            putExtra("TOKEN", token)
                            putExtra("USER_NAME", name)
                        }
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login Gagal: Email atau Password salah", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@LoginActivity, "Terjadi Kesalahan: ${e.message}", Toast.LENGTH_SHORT).show()
                } finally {
                    progressBar.visibility = View.GONE
                    btnLogin.isEnabled = true
                }
            }
        }
    }
}