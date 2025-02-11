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

        title = resources.getString(R.string.AccueilTitle)

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
        items.add(Tache("Faire le tri dans les papiers", 75, 90, Date(2026, 2, 18)))
        items.add(Tache("Organiser les documents", 40, 60, Date(2026, 3, 1)))
        items.add(Tache("Réparer l'ordinateur", 20, 25, Date(2026, 4, 10)))
        items.add(Tache("Planifier les vacances", 60, 40, Date(2026, 5, 30)))
        items.add(Tache("Ranger la chambre", 95, 80, Date(2026, 2, 19)))
        items.add(Tache("Tondre la pelouse", 100, 100, Date(2026, 4, 5)))
        items.add(Tache("Faire les réservations pour le restaurant", 50, 35, Date(2026, 3, 5)))
        items.add(Tache("Vérifier la météo pour la semaine", 10, 15, Date(2026, 2, 13)))
        items.add(Tache("Appeler les parents", 90, 95, Date(2026, 2, 12)))
        items.add(Tache("Mettre à jour le CV", 60, 50, Date(2026, 4, 1)))
        items.add(Tache("Envoyer la lettre de motivation", 30, 20, Date(2026, 3, 25)))
        items.add(Tache("Faire une promenade", 50, 40, Date(2026, 2, 15)))
        items.add(Tache("Préparer un dessert", 80, 60, Date(2026, 2, 18)))
        items.add(Tache("Faire un appel vidéo avec des amis", 100, 100, Date(2026, 2, 15)))
        items.add(Tache("Étudier l'histoire", 70, 50, Date(2026, 6, 5)))
        items.add(Tache("Visiter un musée", 40, 30, Date(2026, 5, 1)))
        items.add(Tache("Régler la facture d'électricité", 20, 10, Date(2026, 2, 20)))
        items.add(Tache("Changer les draps", 60, 50, Date(2026, 3, 10)))
        items.add(Tache("Rappeler le plombier", 80, 70, Date(2026, 2, 25)))
        items.add(Tache("Faire une présentation", 70, 60, Date(2026, 3, 15)))
        items.add(Tache("Acheter des médicaments", 40, 30, Date(2026, 2, 12)))
        items.add(Tache("Organiser un dîner avec des amis", 90, 80, Date(2026, 4, 10)))
        items.add(Tache("Préparer le petit déjeuner", 50, 70, Date(2026, 2, 13)))
        items.add(Tache("Mettre à jour l'application", 30, 25, Date(2026, 3, 5)))
        items.add(Tache("Se rendre à une réunion", 100, 100, Date(2026, 2, 12)))
        items.add(Tache("Nettoyer la cuisine", 80, 75, Date(2026, 3, 12)))
        items.add(Tache("Lire un livre", 60, 50, Date(2026, 6, 1)))
        items.add(Tache("Faire des courses en ligne", 40, 30, Date(2026, 4, 15)))
        items.add(Tache("Faire un don à une association", 10, 5, Date(2026, 5, 10)))
        items.add(Tache("Mettre à jour le blog", 90, 80, Date(2026, 4, 5)))
        items.add(Tache("Réserver un billet de train", 100, 100, Date(2026, 3, 20)))
        items.add(Tache("Faire une vérification des finances", 20, 10, Date(2026, 4, 1)))
        items.add(Tache("Prendre rendez-vous chez le coiffeur", 70, 65, Date(2026, 2, 18)))
        items.add(Tache("Faire une pause café", 100, 100, Date(2026, 2, 13)))
        items.add(Tache("Écrire une lettre", 60, 50, Date(2026, 3, 25)))
        items.add(Tache("Ranger le garage", 40, 30, Date(2026, 5, 1)))
        items.add(Tache("Rechercher une nouvelle série à regarder", 80, 90, Date(2026, 2, 17)))
        items.add(Tache("Assister à un cours en ligne", 50, 60, Date(2026, 3, 10)))
        items.add(Tache("Jouer à un jeu de société", 30, 20, Date(2026, 2, 14)))
        items.add(Tache("Suivre un cours de cuisine", 90, 100, Date(2026, 3, 12)))
        items.add(Tache("Faire des exercices physiques", 70, 60, Date(2026, 2, 25)))
        items.add(Tache("Réparer une fuite d'eau", 60, 50, Date(2026, 4, 20)))
        items.add(Tache("Réorganiser le placard", 40, 30, Date(2026, 5, 25)))
        items.add(Tache("Réfléchir à un projet futur", 50, 40, Date(2026, 3, 30)))
        items.add(Tache("Méditer", 80, 70, Date(2026, 2, 16)))
        items.add(Tache("Écouter un podcast", 20, 10, Date(2026, 2, 20)))
        items.add(Tache("Faire des recherches pour un projet", 60, 50, Date(2026, 4, 5)))
        items.add(Tache("Faire un brainstorming", 40, 30, Date(2026, 5, 5)))
        items.add(Tache("Visiter un parc", 90, 80, Date(2026, 3, 10)))
        items.add(Tache("Lire un article intéressant", 10, 15, Date(2026, 2, 19)))
        items.add(Tache("Acheter des plantes", 30, 25, Date(2026, 3, 18)))
        items.add(Tache("Faire du yoga", 80, 75, Date(2026, 2, 15)))
        items.add(Tache("Cuisiner un plat exotique", 50, 60, Date(2026, 3, 5)))
        items.add(Tache("Faire un nettoyage de printemps", 60, 50, Date(2026, 4, 15)))
        items.add(Tache("Planifier un événement", 100, 100, Date(2026, 5, 12)))
        items.add(Tache("Récupérer un colis", 30, 20, Date(2026, 2, 13)))
        items.add(Tache("Faire du bénévolat", 90, 100, Date(2026, 4, 10)))
        items.add(Tache("Vérifier les e-mails professionnels", 60, 55, Date(2026, 2, 25)))
        items.add(Tache("Fixer un rendez-vous chez le dentiste", 40, 30, Date(2026, 3, 20)))
        items.add(Tache("Faire un plan de voyage", 80, 75, Date(2026, 5, 10)))
        items.add(Tache("Assister à une conférence", 100, 100, Date(2026, 2, 28)))
        items.add(Tache("Changer de forfait téléphonique", 50, 40, Date(2026, 3, 10)))
        items.add(Tache("Écrire un article", 90, 80, Date(2026, 5, 1)))
        items.add(Tache("Préparer un budget mensuel", 60, 50, Date(2026, 3, 15)))
        items.add(Tache("Prendre une photo de famille", 80, 70, Date(2026, 4, 10)))
        items.add(Tache("Réparer la porte de garage", 50, 60, Date(2026, 2, 17)))
        items.add(Tache("Faire du shopping pour des vêtements", 30, 20, Date(2026, 5, 30)))
        items.add(Tache("Nettoyer les fenêtres", 60, 50, Date(2026, 4, 5)))
        items.add(Tache("Préparer un smoothie", 100, 100, Date(2026, 2, 14)))
        items.add(Tache("Laver la voiture", 30, 25, Date(2026, 5, 20)))
        items.add(Tache("Prendre un bain relaxant", 80, 90, Date(2026, 2, 16)))
        items.add(Tache("Organiser une soirée jeux", 70, 60, Date(2026, 3, 10)))
        items.add(Tache("Acheter des fournitures de bureau", 50, 40, Date(2026, 3, 20)))
        items.add(Tache("Vérifier la boîte aux lettres", 90, 80, Date(2026, 2, 18)))
        items.add(Tache("Prendre un rendez-vous pour un vaccin", 100, 100, Date(2026, 2, 25)))
        items.add(Tache("Télécharger une nouvelle application", 60, 70, Date(2026, 3, 1)))
        items.add(Tache("Organiser les photos sur le téléphone", 40, 30, Date(2026, 3, 15)))
        items.add(Tache("Ranger le salon", 40, 20, Date(2026, 3, 1)))
        items.add(Tache("Nettoyer les fenêtres", 60, 50, Date(2026, 4, 5)))
        items.add(Tache("Préparer un smoothie", 100, 100, Date(2026, 2, 14)))
        items.add(Tache("Laver la voiture", 30, 25, Date(2026, 5, 20)))
        items.add(Tache("Prendre un bain relaxant", 80, 90, Date(2026, 2, 16)))
        items.add(Tache("Organiser une soirée jeux", 70, 60, Date(2026, 3, 10)))
        items.add(Tache("Acheter des fournitures de bureau", 50, 40, Date(2026, 3, 20)))
        items.add(Tache("Vérifier la boîte aux lettres", 90, 80, Date(2026, 2, 18)))
        items.add(Tache("Prendre un rendez-vous pour un vaccin", 100, 100, Date(2026, 2, 25)))
        items.add(Tache("Télécharger une nouvelle application", 60, 70, Date(2026, 3, 1)))
        items.add(Tache("Organiser les photos sur le téléphone", 40, 30, Date(2026, 3, 15)))
        items.add(Tache("Réparer une étagère", 50, 45, Date(2026, 4, 10)))
        items.add(Tache("Préparer une présentation", 80, 70, Date(2026, 5, 5)))
        items.add(Tache("Jardinage", 60, 55, Date(2026, 3, 25)))
        items.add(Tache("Faire une vidéo pour les réseaux sociaux", 90, 80, Date(2026, 4, 12)))
        items.add(Tache("Faire une liste de courses", 100, 90, Date(2026, 2, 20)))
        items.add(Tache("Changer la batterie du téléphone", 20, 15, Date(2026, 3, 30)))
        items.add(Tache("Faire du tri dans les vêtements", 50, 60, Date(2026, 5, 10)))
        items.add(Tache("Acheter un cadeau d'anniversaire", 30, 25, Date(2026, 3, 10)))
        items.add(Tache("Organiser une fête d'anniversaire", 40, 35, Date(2026, 6, 1)))
        items.add(Tache("Vérifier les factures", 80, 85, Date(2026, 4, 18)))
        items.add(Tache("Faire un point sur les objectifs de l'année", 60, 50, Date(2026, 5, 15)))
        items.add(Tache("Apprendre une nouvelle langue", 20, 10, Date(2026, 6, 30)))
        items.add(Tache("Faire des courses en ligne", 50, 40, Date(2026, 4, 5)))
        items.add(Tache("Mettre à jour le profil LinkedIn", 30, 25, Date(2026, 3, 25)))
        items.add(Tache("Faire du rangement dans les placards", 70, 60, Date(2026, 5, 12)))
        items.add(Tache("Faire un tri numérique (fichiers, photos)", 90, 80, Date(2026, 4, 8)))
        items.add(Tache("Prendre un café avec un ami", 100, 100, Date(2026, 2, 25)))
        items.add(Tache("Rechercher des idées de décoration pour la maison", 40, 30, Date(2026, 4, 15)))
        items.add(Tache("Répondre aux messages de groupe", 20, 10, Date(2026, 3, 20)))
        items.add(Tache("Apprendre à cuisiner un plat nouveau", 60, 55, Date(2026, 5, 18)))
        items.add(Tache("Revoir les dernières nouvelles", 50, 45, Date(2026, 2, 15)))
        items.add(Tache("Regarder un documentaire", 80, 70, Date(2026, 6, 5)))
        items.add(Tache("Faire une séance de sport à la maison", 90, 85, Date(2026, 3, 10)))
        items.add(Tache("Vérifier les horaires des transports en commun", 40, 30, Date(2026, 3, 25)))
        items.add(Tache("Planifier un week-end en famille", 60, 50, Date(2026, 4, 1)))
        items.add(Tache("Faire le point sur les finances personnelles", 100, 100, Date(2026, 2, 12)))
        items.add(Tache("Prendre un déjeuner équilibré", 70, 65, Date(2026, 2, 10)))
        items.add(Tache("Faire le suivi des commandes en ligne", 50, 45, Date(2026, 3, 28)))
        items.add(Tache("Faire une balade à vélo", 80, 75, Date(2026, 5, 30)))
        items.add(Tache("Écrire un article pour le blog", 90, 80, Date(2026, 4, 1)))
        items.add(Tache("Faire une recherche pour un projet professionnel", 30, 20, Date(2026, 3, 1)))
        items.add(Tache("Réorganiser la bibliothèque", 50, 40, Date(2026, 5, 15)))
        items.add(Tache("Faire une pause déjeuner", 100, 100, Date(2026, 2, 16)))
        items.add(Tache("Écouter une nouvelle chanson", 60, 50, Date(2026, 4, 20)))
        items.add(Tache("Répondre à une enquête", 90, 85, Date(2026, 3, 25)))
        items.add(Tache("Rappeler un collègue", 40, 30, Date(2026, 3, 10)))
        items.add(Tache("Planifier un appel vidéo", 70, 65, Date(2026, 2, 22)))
        items.add(Tache("Lire un chapitre d'un livre", 30, 25, Date(2026, 4, 5)))
        items.add(Tache("Appeler un service client", 80, 75, Date(2026, 3, 3)))
        items.add(Tache("Préparer un petit déjeuner sain", 90, 85, Date(2026, 2, 20)))
        items.add(Tache("Organiser le réfrigérateur", 60, 50, Date(2026, 5, 10)))
        items.add(Tache("Faire une réservation pour un événement", 100, 100, Date(2026, 4, 12)))
        items.add(Tache("Se détendre avec un livre", 40, 30, Date(2026, 2, 28)))
        items.add(Tache("Laver le linge", 50, 40, Date(2026, 3, 10)))
        items.add(Tache("Envoyer une carte de vœux", 30, 20, Date(2026, 5, 1)))
        items.add(Tache("Faire du rangement dans le garage", 80, 70, Date(2026, 4, 1)))
        items.add(Tache("Organiser une réunion virtuelle", 90, 85, Date(2026, 2, 18)))
        items.add(Tache("Faire du bénévolat", 100, 100, Date(2026, 3, 25)))
        items.add(Tache("Écouter un podcast éducatif", 60, 50, Date(2026, 4, 8)))
        items.add(Tache("Visiter un marché", 40, 30, Date(2026, 5, 7)))
        items.add(Tache("Faire du yoga", 70, 60, Date(2026, 3, 17)))
        items.add(Tache("Faire un appel téléphonique professionnel", 90, 85, Date(2026, 2, 14)))
        items.add(Tache("Revoir un film préféré", 100, 100, Date(2026, 6, 3)))
        items.add(Tache("Apprendre une nouvelle compétence", 50, 45, Date(2026, 3, 25)))
        items.add(Tache("Prendre une photo de paysage", 40, 35, Date(2026, 4, 5)))
        items.add(Tache("Faire un suivi avec un client", 70, 60, Date(2026, 3, 10)))
        items.add(Tache("Lire un article scientifique", 30, 20, Date(2026, 5, 1)))
        items.add(Tache("Appeler un vieil ami", 90, 80, Date(2026, 3, 8)))
        items.add(Tache("Réorganiser les tâches professionnelles", 60, 50, Date(2026, 4, 15)))
        items.add(Tache("Récupérer un colis en magasin", 40, 30, Date(2026, 2, 22)))
        items.add(Tache("Préparer un repas équilibré", 100, 100, Date(2026, 3, 10)))
        items.add(Tache("Faire un point sur les objectifs de carrière", 80, 70, Date(2026, 4, 5)))
        items.add(Tache("Réparer une chaise cassée", 50, 45, Date(2026, 3, 25)))
        items.add(Tache("Faire des étirements", 60, 55, Date(2026, 2, 25)))
        items.add(Tache("Écouter de la musique relaxante", 100, 100, Date(2026, 3, 10)))
        items.add(Tache("Planifier une sortie au cinéma", 40, 30, Date(2026, 5, 5)))
        items.add(Tache("Faire un point sur les objectifs mensuels", 80, 70, Date(2026, 2, 28)))
        items.add(Tache("Préparer un plan d'entraînement", 60, 55, Date(2026, 4, 20)))
        items.add(Tache("Faire une promenade dans le parc", 100, 100, Date(2026, 3, 15)))
        items.add(Tache("Mettre à jour l'agenda", 40, 30, Date(2026, 3, 1)))
        items.add(Tache("Visiter un musée", 50, 60, Date(2026, 5, 1)))
        items.add(Tache("Appeler pour prendre un rendez-vous médical", 30, 20, Date(2026, 4, 3)))
        items.add(Tache("Rédiger un rapport professionnel", 60, 70, Date(2026, 3, 10)))
        items.add(Tache("Préparer une salade", 100, 100, Date(2026, 2, 17)))
        items.add(Tache("Faire le ménage dans la chambre", 70, 65, Date(2026, 3, 25)))
        items.add(Tache("Lire un article de blog", 30, 40, Date(2026, 3, 18)))
        items.add(Tache("Faire du bénévolat à l'association locale", 90, 100, Date(2026, 4, 25)))
        items.add(Tache("Changer l'ampoule grillée", 50, 40, Date(2026, 5, 10)))
        items.add(Tache("Écrire une lettre à un ami", 80, 70, Date(2026, 4, 1)))
        items.add(Tache("Réorganiser la cuisine", 60, 55, Date(2026, 3, 10)))
        items.add(Tache("Faire un tirage photo", 30, 25, Date(2026, 4, 18)))
        items.add(Tache("Jouer à un jeu vidéo", 50, 40, Date(2026, 3, 5)))
        items.add(Tache("Faire un défi sportif", 100, 90, Date(2026, 5, 25)))
        items.add(Tache("Préparer un café spécial", 80, 85, Date(2026, 3, 8)))
        items.add(Tache("Réviser pour l'examen de maths", 60, 50, Date(2026, 4, 1)))
        items.add(Tache("Faire une photo de famille", 70, 80, Date(2026, 5, 1)))
        items.add(Tache("Visiter un parc d'attractions", 90, 100, Date(2026, 6, 15)))
        items.add(Tache("Faire un point sur les dépenses mensuelles", 50, 45, Date(2026, 3, 20)))
        items.add(Tache("Faire du tri dans les livres", 60, 55, Date(2026, 4, 30)))
        items.add(Tache("Ranger les étagères", 80, 90, Date(2026, 5, 15)))
        items.add(Tache("Appeler pour réserver des billets de concert", 50, 60, Date(2026, 4, 5)))
        items.add(Tache("Participer à un webinaire", 70, 80, Date(2026, 3, 10)))
        items.add(Tache("Envoyer des invitations pour une soirée", 40, 35, Date(2026, 5, 1)))
        items.add(Tache("Organiser un dîner en famille", 60, 50, Date(2026, 3, 25)))
        items.add(Tache("Répondre à un sondage", 100, 100, Date(2026, 4, 2)))
        items.add(Tache("Vérifier les stocks à la maison", 50, 40, Date(2026, 3, 15)))
        items.add(Tache("Faire une mise à jour de l'ordinateur", 60, 55, Date(2026, 5, 3)))
        items.add(Tache("Faire une séance photo", 90, 80, Date(2026, 2, 19)))
        items.add(Tache("Rechercher des informations pour un projet", 80, 70, Date(2026, 5, 10)))
        items.add(Tache("Faire une collecte pour une association", 50, 60, Date(2026, 4, 1)))
        items.add(Tache("Écouter un audiobook", 40, 35, Date(2026, 3, 5)))
        items.add(Tache("Faire un dessin", 30, 20, Date(2026, 4, 20)))
        items.add(Tache("Répondre à un email important", 90, 80, Date(2026, 3, 30)))
        items.add(Tache("Faire une réunion de travail", 100, 100, Date(2026, 2, 15)))
        items.add(Tache("Réparer un appareil électroménager", 50, 45, Date(2026, 4, 18)))
        items.add(Tache("Préparer un brunch", 80, 70, Date(2026, 3, 5)))
        items.add(Tache("Vérifier les prévisions météo", 60, 50, Date(2026, 5, 3)))
        items.add(Tache("Faire un don à une organisation", 100, 100, Date(2026, 3, 25)))
        items.add(Tache("Réparer une fuite d'eau", 50, 40, Date(2026, 4, 1)))
        items.add(Tache("Faire une séance de relaxation", 80, 70, Date(2026, 5, 15)))
        items.add(Tache("Assister à un événement sportif", 100, 90, Date(2026, 4, 5)))
        items.add(Tache("Préparer un cadeau de Noël", 50, 45, Date(2026, 12, 20)))
        items.add(Tache("Écrire dans un journal", 60, 55, Date(2026, 3, 12)))
        items.add(Tache("Faire une activité manuelle", 40, 30, Date(2026, 5, 25)))
        items.add(Tache("Créer un tableau de vision", 70, 65, Date(2026, 4, 18)))
        items.add(Tache("Faire un gâteau", 80, 90, Date(2026, 3, 8)))
        items.add(Tache("Réserver un hôtel pour les vacances", 60, 55, Date(2026, 5, 5)))
        items.add(Tache("Prendre des photos de paysage", 40, 30, Date(2026, 4, 15)))
        items.add(Tache("Faire une activité sportive en extérieur", 100, 100, Date(2026, 3, 1)))
        items.add(Tache("Faire un défi créatif", 60, 50, Date(2026, 4, 10)))
        items.add(Tache("Faire une soirée cinéma à la maison", 50, 60, Date(2026, 3, 20)))
        adapter.submitList(items) // Pour changer le contenu de la liste, utiliser submitList de l'adapteur
    }
}