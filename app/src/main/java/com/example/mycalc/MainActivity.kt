package com.example.mycalc

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mycalc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        //setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        //val inputEdit = findViewById<TextInputLayout>(R.id.inputEdit)
        val list = listOf(
            ItemDetails("Apple", "A round fruit with a red, green, or yellow skin, and a crisp, sweet or tart flesh."),
            ItemDetails("Banana", "A curved, yellow fruit with a soft, sweet flesh and a tough, yellow peel."),
            ItemDetails("Orange", "A citrus fruit with a bright orange, bumpy rind and a juicy, segmented pulp."),
            ItemDetails("Grapes", "Small, round, sweet fruits that grow in clusters."),
            ItemDetails("Papaya", "A round fruit with a red, green, or yellow skin, and a crisp, sweet or tart flesh."),
            ItemDetails("Mango", "A curved, yellow fruit with a soft, sweet flesh and a tough, yellow peel."),
            ItemDetails("Watermelon", "A round fruit with a red, green, or yellow skin, and a crisp, sweet or tart flesh."),
            ItemDetails("Guava", "A curved, yellow fruit with a soft, sweet flesh and a tough, yellow peel."),
            ItemDetails("Mandarin", "A citrus fruit with a bright orange, bumpy rind and a juicy, segmented pulp."),
            ItemDetails("Strawberry", "Small, round, sweet fruits that grow in clusters.")
        )
        val adpt = ListClassAdapter(list)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adpt

    }
}