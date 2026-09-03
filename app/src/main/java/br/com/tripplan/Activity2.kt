package br.com.tripplan

import android.app.Activity
import android.os.Bundle

class Activity2 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity2)

        val destino = intent.getStringExtra("destino")
        val dataPartida = intent.getStringExtra("dataPartida")
        val dataRetorno = intent.getStringExtra("dataRetorno")
        val preferencias = intent.getStringExtra("preferencias")

        println("=== DADOS DA VIAGEM ===")
        println("Destino: $destino")
        println("Data de partida: $dataPartida")
        println("Data de retorno: $dataRetorno")
        println("Preferências: $preferencias")
    }
}