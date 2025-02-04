package com.example.tp_daniklussier

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_daniklussier.databinding.ActivityConsultationBinding
import com.example.tp_daniklussier.databinding.ActivityCreationBinding

class Creation : AppCompatActivity() {

    private lateinit var binding: ActivityCreationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCreationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Creation"
    }
}