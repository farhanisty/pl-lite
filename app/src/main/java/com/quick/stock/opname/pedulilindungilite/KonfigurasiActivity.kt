package com.quick.stock.opname.pedulilindungilite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quick.stock.opname.pedulilindungilite.databinding.ActivityKonfigurasiBinding

class KonfigurasiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKonfigurasiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKonfigurasiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){
        binding.btnScan.setOnClickListener {
            with(binding){
                Database.nama = etNama.text.toString()
                Database.jam = etJam.text.toString()
                Database.tanggal = etTanggal.text.toString()
            }
            startActivity(Intent(this@KonfigurasiActivity, ScannerActivity::class.java))
        }
    }
}