package br.com.tripplan

data class Atividade(
    val nome: String,
    val descricao: String,
    val categoria: String,
    val duracao: String,
    val dificuldade: String,
    val imagem: Int
)