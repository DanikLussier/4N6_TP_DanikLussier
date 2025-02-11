package com.example.tp_daniklussier

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_daniklussier.databinding.ActivityConsultationBinding
import com.example.tp_daniklussier.databinding.ActivityInscriptionBinding

class Inscription : AppCompatActivity() {

    private lateinit var binding: ActivityInscriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInscriptionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = resources.getString(R.string.InscriptionTitle)

        binding.CreateAccount.setOnClickListener {
            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}