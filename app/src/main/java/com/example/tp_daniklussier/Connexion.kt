package com.example.tp_daniklussier

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_daniklussier.databinding.ActivityConnexionBinding
import com.example.tp_daniklussier.databinding.ActivityMainBinding

class Connexion : AppCompatActivity() {

    private lateinit var binding: ActivityConnexionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnexionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Connexion"

        binding.Connexion.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.Inscription.setOnClickListener {
            val intent = Intent(this, Inscription::class.java)
            startActivity(intent)
        }
    }
}