package com.example.tugasakhirpasien

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirpasien.adapter.PatientAdapter
import com.example.tugasakhirpasien.network.ApiService
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val rvPatients = findViewById<RecyclerView>(R.id.rvPatients)
        val progressBar = findViewById<ProgressBar>(R.id.progressBarPatient)

        val token = intent.getStringExtra("TOKEN")
        val userName = intent.getStringExtra("USER_NAME")

        tvWelcome.text = "Selamat Datang, $userName"

        rvPatients.layoutManager = LinearLayoutManager(this)

        val apiService = ApiService.create()

        if (token != null) {
            progressBar.visibility = View.VISIBLE

            lifecycleScope.launch {
                try {
                    val authHeader = "Bearer $token"
                    val response = apiService.getPatients(authHeader)

                    if (response.isSuccessful && response.body()?.success == true) {
                        val patientList = response.body()?.data ?: emptyList()
                        val adapter = PatientAdapter(patientList)
                        rvPatients.adapter = adapter
                    } else {
                        Toast.makeText(this@MainActivity, "Gagal mengambil data pasien", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Kesalahan Jaringan: ${e.message}", Toast.LENGTH_SHORT).show()
                } finally {
                    progressBar.visibility = View.GONE
                }
            }
        }
    }
}