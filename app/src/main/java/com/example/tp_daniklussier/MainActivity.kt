package com.example.tp_daniklussier

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.tp_daniklussier.adapters.TacheAdapter
import com.example.tp_daniklussier.databinding.ActivityConsultationBinding
import com.example.tp_daniklussier.databinding.ActivityMainBinding
import com.example.tp_daniklussier.models.Tache
import com.google.android.material.snackbar.Snackbar
import java.time.Clock
import java.time.LocalDateTime
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TacheAdapter
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDrawer()

        title = "Accueil"

        binding.fab.setOnClickListener{
            val intent = Intent(this, Creation::class.java)
            startActivity(intent)
        }

        setupRecycler()
        fillRecycler()
    }

    private fun setupDrawer() {
        setupDrawerApplicationBar()
        setupDrawerItemSelected()
    }

    private fun setupDrawerApplicationBar() {
        // Afficher le menu hamburger sur la barre d'application
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Lier le tiroir de navigation à l'activité
        // R.string.ouvert et R.string.ferme sont des strings de ressource.
        // Référez-vous à la recette pour les strings de ressource pour voir comment les ajouter :
        // https://info.cegepmontpetit.ca/3N5-Prog3/recettes/ressources-string
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.dlTiroir, R.string.nav_ouvert, R.string.nav_ferme)

        // Faire en sorte que le menu hamburger se transforme en flèche au clic, et vis versa
        binding.dlTiroir.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun setupDrawerItemSelected() {
        // Réagir aux clics sur les actions de menu
        binding.nvTiroir.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.accueil_item -> {
                    Snackbar.make(binding.root, "On va à l'accueil!", Snackbar.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.ajouter_item -> {
                    Snackbar.make(
                        binding.root, "On va ajouter quelque chose!", Snackbar.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, Creation::class.java)
                    startActivity(intent)
                }
                R.id.deconnexion -> {
                    Snackbar.make(
                        binding.root, "Déconnexion !!!", Snackbar.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, Connexion::class.java)
                    startActivity(intent)
                }
            }
            false
        }
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
                "Nom $i", 50, 80, Date(2026, 5, 5)
            ))
        }
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}