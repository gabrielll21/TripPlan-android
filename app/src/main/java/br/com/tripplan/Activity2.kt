package br.com.tripplan

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class Activity2 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2)

        val destino = intent.getStringExtra("destino") ?: ""
        val dataPartida = intent.getStringExtra("dataPartida") ?: ""
        val dataRetorno = intent.getStringExtra("dataRetorno") ?: ""
        val preferencias = intent.getStringExtra("preferencias") ?: ""

        val txtDestino = findViewById<TextView>(R.id.txtDestino)
        val txtPreferencias = findViewById<TextView>(R.id.txtPreferencias)

        txtDestino.text = "Destino: $destino"
        txtPreferencias.text = "Preferências: $preferencias"

        println("=== ACTIVITY 2 ===")
        println("Destino: $destino")
        println("Data de partida: $dataPartida")
        println("Data de retorno: $dataRetorno")
        println("Preferências: $preferencias")
    }
}