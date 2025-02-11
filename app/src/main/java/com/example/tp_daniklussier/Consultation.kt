package com.example.tp_daniklussier

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp_daniklussier.databinding.ActivityConsultationBinding
import com.example.tp_daniklussier.databinding.ActivityMainBinding
import com.example.tp_daniklussier.databinding.NavHeaderBinding

class Consultation : AppCompatActivity() {

    private lateinit var binding: ActivityConsultationBinding
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupDrawer()

        title = "Consultation"

        binding.numberPicker.minValue = 0
        binding.numberPicker.maxValue = 100
        binding.numberPicker.value = 80
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
                R.id.accueil_item -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
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
}