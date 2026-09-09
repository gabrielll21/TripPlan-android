package br.com.tripplan

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Activity2 : Activity() {

    private lateinit var atividades: List<Atividade>

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

        criarAtividades()

        val atividadesFiltradas = filtrarAtividades(preferencias)

        val recyclerAtividades = findViewById<RecyclerView>(R.id.recyclerAtividades)
        recyclerAtividades.layoutManager = LinearLayoutManager(this)
        recyclerAtividades.adapter = AtividadeAdapter(atividadesFiltradas) { atividade ->
            println("=== TRANSIÇÃO PARA A ACTIVITY 3 ===")
            println("Nome: ${atividade.nome}")
            println("Descrição: ${atividade.descricao}")
            println("Categoria: ${atividade.categoria}")
            println("Duração: ${atividade.duracao}")
            println("Dificuldade: ${atividade.dificuldade}")

            val intent = Intent(this, Activity3::class.java)
            intent.putExtra("nome", atividade.nome)
            intent.putExtra("descricao", atividade.descricao)
            intent.putExtra("categoria", atividade.categoria)
            intent.putExtra("duracao", atividade.duracao)
            intent.putExtra("dificuldade", atividade.dificuldade)
            intent.putExtra("imagem", atividade.imagem)
            startActivity(intent)
        }

        println("=== ATIVIDADES DISPONÍVEIS ===")

        for (atividade in atividadesFiltradas) {
            println("${atividade.nome} - ${atividade.categoria}")
        }
    }

    private fun criarAtividades() {

        atividades = listOf(

            Atividade(
                nome = "Trilha da Pedra Bonita",
                descricao = "Explore uma trilha com uma bela vista do Rio de Janeiro.",
                categoria = "Aventura",
                duracao = "3 horas",
                dificuldade = "Moderada",
                imagem = R.drawable.ic_launcher_foreground
            ),

            Atividade(
                nome = "Passeio de barco",
                descricao = "Conheça a cidade através de um passeio pelo mar.",
                categoria = "Aventura",
                duracao = "2 horas",
                dificuldade = "Fácil",
                imagem = R.drawable.ic_launcher_foreground
            ),

            Atividade(
                nome = "Museu do Amanhã",
                descricao = "Visite um dos principais museus do Rio de Janeiro.",
                categoria = "Cultura",
                duracao = "2 horas",
                dificuldade = "Fácil",
                imagem = R.drawable.ic_launcher_foreground
            ),

            Atividade(
                nome = "Cristo Redentor",
                descricao = "Visite um dos principais pontos turísticos do Brasil.",
                categoria = "Cultura",
                duracao = "3 horas",
                dificuldade = "Fácil",
                imagem = R.drawable.ic_launcher_foreground
            ),

            Atividade(
                nome = "Praia de Copacabana",
                descricao = "Aproveite um dos lugares mais famosos do Rio.",
                categoria = "Praia",
                duracao = "4 horas",
                dificuldade = "Fácil",
                imagem = R.drawable.ic_launcher_foreground
            ),

            Atividade(
                nome = "Praia de Ipanema",
                descricao = "Relaxe e aproveite o dia em uma das praias mais famosas.",
                categoria = "Praia",
                duracao = "4 horas",
                dificuldade = "Fácil",
                imagem = R.drawable.ic_launcher_foreground
            )
        )
    }

    private fun filtrarAtividades(preferencias: String): List<Atividade> {

        if (preferencias.isBlank()) {
            return emptyList()
        }

        val preferenciasSelecionadas = preferencias
            .split(",")
            .map { it.trim() }

        return atividades.filter { atividade ->
            preferenciasSelecionadas.contains(atividade.categoria)
        }
    }
}