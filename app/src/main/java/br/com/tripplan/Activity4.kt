package br.com.tripplan

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class Activity4 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity4)

        val destino = intent.getStringExtra("destino") ?: ""
        val dataPartida = intent.getStringExtra("dataPartida") ?: ""
        val dataRetorno = intent.getStringExtra("dataRetorno") ?: ""
        val preferencias = intent.getStringExtra("preferencias") ?: ""
        val nome = intent.getStringExtra("nome") ?: ""
        val descricao = intent.getStringExtra("descricao") ?: ""
        val categoria = intent.getStringExtra("categoria") ?: ""
        val duracao = intent.getStringExtra("duracao") ?: ""
        val dificuldade = intent.getStringExtra("dificuldade") ?: ""
        val imagem = intent.getIntExtra("imagem", 0)

        val txtResumoDestino = findViewById<TextView>(R.id.txtResumoDestino)
        val txtResumoDataPartida = findViewById<TextView>(R.id.txtResumoDataPartida)
        val txtResumoDataRetorno = findViewById<TextView>(R.id.txtResumoDataRetorno)
        val txtResumoPreferencias = findViewById<TextView>(R.id.txtResumoPreferencias)
        val imgResumoAtividade = findViewById<ImageView>(R.id.imgResumoAtividade)
        val txtResumoNome = findViewById<TextView>(R.id.txtResumoNome)
        val txtResumoDescricao = findViewById<TextView>(R.id.txtResumoDescricao)
        val txtResumoCategoria = findViewById<TextView>(R.id.txtResumoCategoria)
        val txtResumoDuracao = findViewById<TextView>(R.id.txtResumoDuracao)
        val txtResumoDificuldade = findViewById<TextView>(R.id.txtResumoDificuldade)
        val btnFinalizar = findViewById<Button>(R.id.btnFinalizar)

        txtResumoDestino.text = "Destino: $destino"
        txtResumoDataPartida.text = "Data de partida: $dataPartida"
        txtResumoDataRetorno.text = "Data de retorno: $dataRetorno"
        txtResumoPreferencias.text = "Preferências: $preferencias"
        txtResumoNome.text = nome
        txtResumoDescricao.text = descricao
        txtResumoCategoria.text = "Categoria: $categoria"
        txtResumoDuracao.text = "Duração escolhida: $duracao"
        txtResumoDificuldade.text = "Dificuldade escolhida: $dificuldade"

        if (imagem != 0) {
            imgResumoAtividade.setImageResource(imagem)
        }

        btnFinalizar.setOnClickListener {
            println("=== PLANEJAMENTO FINALIZADO ===")
            println("Destino: $destino")
            println("Data de partida: $dataPartida")
            println("Data de retorno: $dataRetorno")
            println("Preferências: $preferencias")
            println("Nome da atividade: $nome")
            println("Descrição: $descricao")
            println("Categoria: $categoria")
            println("Duração escolhida: $duracao")
            println("Dificuldade escolhida: $dificuldade")
            println("Imagem: $imagem")
        }
    }
}
