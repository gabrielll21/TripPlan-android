package br.com.tripplan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.TextView

class Activity3 : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activity3)

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

        val imgAtividade = findViewById<ImageView>(R.id.imgAtividade)
        val txtNomeAtividade = findViewById<TextView>(R.id.txtNomeAtividade)
        val txtDescricaoAtividade = findViewById<TextView>(R.id.txtDescricaoAtividade)
        val txtCategoriaAtividade = findViewById<TextView>(R.id.txtCategoriaAtividade)
        val txtDuracaoAtividade = findViewById<TextView>(R.id.txtDuracaoAtividade)
        val seekDuracao = findViewById<SeekBar>(R.id.seekDuracao)
        val txtDuracaoEscolhida = findViewById<TextView>(R.id.txtDuracaoEscolhida)
        val radioGroupDificuldade = findViewById<RadioGroup>(R.id.radioGroupDificuldade)
        val btnConfirmarAtividade = findViewById<Button>(R.id.btnConfirmarAtividade)

        if (imagem != 0) {
            imgAtividade.setImageResource(imagem)
        }
        txtNomeAtividade.text = nome
        txtDescricaoAtividade.text = descricao
        txtCategoriaAtividade.text = "Categoria: $categoria"
        txtDuracaoAtividade.text = "Duração: $duracao"

        val duracaoInicial = duracao.filter { it.isDigit() }
            .toIntOrNull()
            ?.coerceIn(1, seekDuracao.max)
            ?: 1

        seekDuracao.min = 1
        seekDuracao.progress = duracaoInicial
        atualizarDuracaoEscolhida(txtDuracaoEscolhida, duracaoInicial)

        when (dificuldade.lowercase()) {
            "fácil", "facil" -> radioGroupDificuldade.check(R.id.rbFacil)
            "moderada" -> radioGroupDificuldade.check(R.id.rbModerada)
            "difícil", "dificil" -> radioGroupDificuldade.check(R.id.rbDificil)
        }

        seekDuracao.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                atualizarDuracaoEscolhida(txtDuracaoEscolhida, progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) = Unit

            override fun onStopTrackingTouch(seekBar: SeekBar?) = Unit
        })

        btnConfirmarAtividade.setOnClickListener {
            val dificuldadeSelecionada = radioGroupDificuldade.checkedRadioButtonId
                .takeIf { it != -1 }
                ?.let { findViewById<RadioButton>(it).text.toString() }
                ?: dificuldade

            val unidadeDuracao = if (seekDuracao.progress == 1) "hora" else "horas"
            val duracaoSelecionada = "${seekDuracao.progress} $unidadeDuracao"

            println("=== TRANSIÇÃO PARA ACTIVITY 4 ===")
            println("Destino: $destino")
            println("Data de partida: $dataPartida")
            println("Data de retorno: $dataRetorno")
            println("Preferências: $preferencias")
            println("Nome: $nome")
            println("Descrição: $descricao")
            println("Categoria: $categoria")
            println("Duração: $duracaoSelecionada")
            println("Dificuldade: $dificuldadeSelecionada")
            println("Imagem: $imagem")

            val intent = Intent(this, Activity4::class.java)
            intent.putExtra("destino", destino)
            intent.putExtra("dataPartida", dataPartida)
            intent.putExtra("dataRetorno", dataRetorno)
            intent.putExtra("preferencias", preferencias)
            intent.putExtra("nome", nome)
            intent.putExtra("descricao", descricao)
            intent.putExtra("categoria", categoria)
            intent.putExtra("duracao", duracaoSelecionada)
            intent.putExtra("dificuldade", dificuldadeSelecionada)
            intent.putExtra("imagem", imagem)
            startActivity(intent)
        }
    }

    private fun atualizarDuracaoEscolhida(textView: TextView, duracao: Int) {
        val unidade = if (duracao == 1) "hora" else "horas"
        textView.text = "Duração escolhida: $duracao $unidade"
    }
}
