package com.quick.stock.opname.pedulilindungilite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.quick.stock.opname.pedulilindungilite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){
        with(binding){
            tvNama.text = Database.nama
            tvDate.text = Database.tanggal
            tvTime.text = Database.jam
        }
    }
}