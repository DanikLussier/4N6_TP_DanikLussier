package com.example.tp_daniklussier

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.tp_daniklussier.adapters.TacheAdapter
import com.example.tp_daniklussier.databinding.ActivityConsultationBinding
import com.example.tp_daniklussier.databinding.ActivityMainBinding
import com.example.tp_daniklussier.models.Tache
import java.time.Clock
import java.time.LocalDateTime
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TacheAdapter

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        title = "Accueil"

        binding.fab.setOnClickListener{
            val intent = Intent(this, Creation::class.java)
            startActivity(intent)
        }

        setupRecycler()
        fillRecycler()
    }

    private fun setupRecycler() {
        adapter = TacheAdapter() // Créer l'adapteur
        binding.rvTacheAdapter.adapter = adapter // Assigner l'adapteur au RecyclerView
        binding.rvTacheAdapter.setHasFixedSize(true) // Option pour améliorer les performances
        binding.rvTacheAdapter.addItemDecoration( // Ajouter un séparateur entre chaque élément
            DividerItemDecoration(
                binding.rvTacheAdapter.context, DividerItemDecoration.VERTICAL
            )
        )
    }

    private fun fillRecycler() {
        val items: MutableList<Tache> = mutableListOf()
        for (i in 1..200) {
            val sexe: String
            if (i % 2 == 0) {
                sexe = "Homme"
            } else {
                sexe = "Femme"
            }
            items.add(Tache(
                "Nom $i", null, null, 50
            ))
        }
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}