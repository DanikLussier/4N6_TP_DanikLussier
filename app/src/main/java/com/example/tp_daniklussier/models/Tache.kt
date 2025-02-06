package com.example.tp_daniklussier.models

import android.health.connect.datatypes.units.Percentage
import java.time.LocalDateTime
import java.util.Date

data class Tache(val nom: String, val avancement: Int, val tempsEcoule: Int, val dateEcheance: Date)