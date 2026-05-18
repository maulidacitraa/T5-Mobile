package com.example.tugasakhirpasien.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tugasakhirpasien.R
import com.example.tugasakhirpasien.model.Patient

class PatientAdapter(private val patients: List<Patient>) :
    RecyclerView.Adapter<PatientAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNama: TextView = view.findViewById(R.id.tvNama)
        val tvGenderTgl: TextView = view.findViewById(R.id.tvGenderTgl)
        val tvAlamat: TextView = view.findViewById(R.id.tvAlamat)
        val tvTelepon: TextView = view.findViewById(R.id.tvTelepon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_patient, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient = patients[position]
        holder.tvNama.text = patient.nama

        val gender = if (patient.jenisKelamin == "L") "Laki-laki" else "Perempuan"
        holder.tvGenderTgl.text = "$gender • ${patient.tanggalLahir}"

        holder.tvAlamat.text = "Alamat: ${patient.alamat}"
        holder.tvTelepon.text = "Telp: ${patient.noTelepon}"
    }

    override fun getItemCount(): Int = patients.size
}