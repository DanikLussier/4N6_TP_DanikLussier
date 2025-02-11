package com.example.tp_daniklussier

import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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
import com.example.tp_daniklussier.databinding.NavHeaderBinding
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
        setupDrawerHeader()
    }

    private fun setupDrawerApplicationBar() {
        // Afficher le menu hamburger sur la barre d'application
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        // Lier le tiroir de navigation à l'activité
        // R.string.ouvert et R.string.ferme sont des strings de ressource.
        // Référez-vous à la recette pour les strings de ressource pour voir comment les ajouter :
        // https://info.cegepmontpetit.ca/3N5-Prog3/recettes/ressources-string
        actionBarDrawerToggle = ActionBarDrawerToggle(this, binding.dlDrawer, R.string.nav_ouvert, R.string.nav_ferme)

        // Faire en sorte que le menu hamburger se transforme en flèche au clic, et vis versa
        binding.dlDrawer.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()
    }

    private fun setupDrawerItemSelected() {
        // Réagir aux clics sur les actions de menu
        binding.nvTiroir.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.ajouter_item -> {
                    val intent = Intent(this, Creation::class.java)
                    startActivity(intent)
                }
                R.id.deconnexion -> {
                    val intent = Intent(this, Connexion::class.java)
                    startActivity(intent)
                }
            }
            false
        }
    }

    private fun setupDrawerHeader() {
        // Si on veut avoir du contenu dynamique dans l'en-tête,
        val headerBinding: NavHeaderBinding = NavHeaderBinding.bind(binding.nvTiroir.getHeaderView(0))
        headerBinding.headertext.text = "Danik Lussier"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Réagir au clic sur le menu hamburger
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
        super.onConfigurationChanged(newConfig)
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
        items.add(Tache("Faire la lessive", 50, 80, Date(2026, 5, 5)))
        items.add(Tache("Faire les courses", 40, 30, Date(2026, 3, 15)))
        items.add(Tache("Préparer le dîner", 70, 60, Date(2026, 2, 20)))
        items.add(Tache("Répondre aux emails", 20, 10, Date(2026, 2, 14)))
        items.add(Tache("Faire le ménage", 80, 95, Date(2026, 3, 25)))
        items.add(Tache("Réviser pour l'examen", 60, 50, Date(2026, 6, 10)))
        items.add(Tache("Appeler le médecin", 90, 100, Date(2026, 2, 12)))
        items.add(Tache("Faire les impôts", 30, 40, Date(2026, 4, 1)))
        items.add(Tache("Préparer le rapport de travail", 50, 70, Date(2026, 2, 28)))
        items.add(Tache("Nettoyer la voiture", 10, 5, Date(2026, 5, 15)))
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}