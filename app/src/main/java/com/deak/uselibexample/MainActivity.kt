package com.deak.uselibexample

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.deak.dkuilibrary.layout.RoundLinearLayout
import com.deak.uselibexample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        findViewById<RoundLinearLayout>(R.id.ll_round_layout).setOnClickListener {
            startActivity(Intent(this,RoundLayoutActivity::class.java))
        }
        binding.tvDkCustom.setOnClickListener {
            startActivity(Intent(this,DemoTextViewActivity::class.java))
        }
        binding.tvDKNetwork.setOnClickListener {
            startActivity(Intent(this,ConnectedUtilsActivity::class.java))
        }
    }
}