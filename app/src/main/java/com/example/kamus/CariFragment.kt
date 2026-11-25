package com.example.kamus

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

// Latihan lanjutan 2: pencarian langsung dengan TextWatcher (tanpa tombol Cari)
class CariFragment : Fragment(R.layout.fragment_cari) {

    // Menyimpan pencarian yang sedang berjalan, supaya bisa dibatalkan
    // saat pengguna mengetik huruf berikutnya
    private var jobCari: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dao = KamusDatabase.getInstance(requireContext()).kataDao()
        val etCari = view.findViewById<EditText>(R.id.etCari)
        val txtKosong = view.findViewById<TextView>(R.id.txtKosong)
        val rvHasil = view.findViewById<RecyclerView>(R.id.rvHasil)

        val adapter = KataAdapter()
        rvHasil.layoutManager = LinearLayoutManager(requireContext())
        rvHasil.adapter = adapter

        fun cari(teks: String) {
            jobCari?.cancel()
            jobCari = viewLifecycleOwner.lifecycleScope.launch {
                val hasil = dao.cari("%$teks%")
                adapter.submitList(hasil)
                txtKosong.visibility = if (hasil.isEmpty()) View.VISIBLE else View.GONE
            }
        }

        // Setiap teks berubah, hasil langsung diperbarui
        etCari.doAfterTextChanged { cari(it.toString().trim()) }

        // Tampilkan semua kata saat tab Cari pertama kali dibuka
        cari(etCari.text.toString().trim())
    }
}
