package com.ydh.praktikumandroid7.ui.user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.UserData
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ydh.praktikumandroid7.databinding.ActivityUserBinding


class UserActivity : AppCompatActivity() {

    private lateinit var binding:ActivityUserBinding
    private val list = ArrayList<UserData>()
    private val viewModel: UserViewModel by lazy {
        ViewModelProvider(this).get(UserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvUsers.setHasFixedSize(true)

        binding.progressBarUser.visibility = View.VISIBLE

        getListUser()

    }

    private fun getListUser() {
        viewModel.response.observe(this, {
            binding.progressBarUser.visibility = View.INVISIBLE
            list.addAll(it.data)
            binding.rvUsers.layoutManager = LinearLayoutManager(this)
            val listUserAdapter = ListUserAdapter(list)
            binding.rvUsers.adapter = listUserAdapter
        })
    }
}






