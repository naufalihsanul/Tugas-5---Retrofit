package com.example.tugas5_retrofit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tugas5_retrofit.databinding.ItemPasienBinding

class PasienAdapter(private val listPasien: List<Pasien>) :
    RecyclerView.Adapter<PasienAdapter.PasienViewHolder>() {

    inner class PasienViewHolder(private val binding: ItemPasienBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pasien: Pasien) {
            binding.tvNamaPasien.text = pasien.nama
            binding.tvTglLahir.text = "Tanggal Lahir: ${pasien.tanggalLahir}"
            binding.tvJenisKelamin.text = "Jenis Kelamin: ${pasien.jenisKelamin}"
            binding.tvAlamat.text = "Alamat: ${pasien.alamat}"
            binding.tvNoTelepon.text = "No. Telepon: ${pasien.noTelepon}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasienViewHolder {
        val binding = ItemPasienBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PasienViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PasienViewHolder, position: Int) {
        holder.bind(listPasien[position])
    }

    override fun getItemCount(): Int = listPasien.size
}