package com.quanticheart.basedialogfragment

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.quanticheart.basedialogfragment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fullNoCancel.setOnClickListener {
            showTest5Dialog()
        }

        binding.fullAnim.setOnClickListener {
            showTest4Dialog()
        }

        binding.full.setOnClickListener {
            showTest3Dialog()
        }

        binding.simple.setOnClickListener {
            showTest1Dialog()
        }

        binding.simpleWithBundle.setOnClickListener {
            showTest2Dialog(bundleOf().apply {
                putString("name", "TEST With Bundle")
            })
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}