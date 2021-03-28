package com.ydh.praktikumandroid7.ui.jenisbarang

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydh.praktikumandroid7.JenisbarangPostActivity
import com.ydh.praktikumandroid7.databinding.ActivityJenisbarangBinding
import com.ydh.praktikumandroid7.model.JenisbarangData

class JenisbarangActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJenisbarangBinding
    private val list = ArrayList<JenisbarangData>()
    private val viewModel: JenisbarangViewModel by lazy {
        ViewModelProvider(this).get(JenisbarangViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJenisbarangBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvJenisbarang.setHasFixedSize(true)

        binding.progressBarJenisbarang.visibility = View.VISIBLE

        getListJenisbarang()

        binding.btTambahJenisbarang.setOnClickListener{
            val intent = Intent(this, JenisbarangPostActivity::class.java)
            startActivity(intent)
        }

    }

    private fun getListJenisbarang() {
        viewModel.response.observe(this, {
            binding.progressBarJenisbarang.visibility = View.INVISIBLE
            list.addAll(it.data)
            binding.rvJenisbarang.layoutManager = LinearLayoutManager(this)
            val listJenisbarangAdapter = ListJenisbarangAdapter(list)
            binding.rvJenisbarang.adapter = listJenisbarangAdapter
        })
    }


}