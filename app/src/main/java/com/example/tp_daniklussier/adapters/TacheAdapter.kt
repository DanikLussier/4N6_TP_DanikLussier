package com.example.tp_daniklussier.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.tp_daniklussier.Consultation
import com.example.tp_daniklussier.databinding.TacheItemBinding
import com.example.tp_daniklussier.models.Tache

class TacheAdapter : ListAdapter<Tache, TacheAdapter.PersonneItemViewHolder>(PersonneItemDiffCallback) {

    // binding nous permet d'accéder à tout le champs de notre layout personne_item.xml
    inner class PersonneItemViewHolder(private val binding: TacheItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tache: Tache) {
            binding.nomTache.text = tache.nom
            binding.valeurCompletion.text = tache.avancement.toString() + "%"
            binding.valeurTempsEcoule.text = tache.tempsEcoule.toString() + "%"
            binding.valeurDateEcheance.text =
                tache.dateEcheance.year.toString() + "/" +
                tache.dateEcheance.month.toString() + "/" +
                tache.dateEcheance.date.toString()

            binding.background.setOnClickListener {
                val intent = Intent(binding.root.context, Consultation::class.java)
                binding.root.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonneItemViewHolder {
        val binding: TacheItemBinding = TacheItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PersonneItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonneItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object PersonneItemDiffCallback : DiffUtil.ItemCallback<Tache>() {
    override fun areItemsTheSame(oldItem: Tache, newItem: Tache): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Tache, newItem: Tache): Boolean {
        return  oldItem.nom == newItem.nom &&
                oldItem.avancement == newItem.avancement &&
                oldItem.dateEcheance == newItem.dateEcheance &&
                oldItem.tempsEcoule == newItem.tempsEcoule
    }
}