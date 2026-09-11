package br.com.tripplan

import com.google.android.libraries.places.api.model.PhotoMetadata

data class Atividade(
    val nome: String,
    val descricao: String,
    val categoria: String,
    val duracao: String,
    val dificuldade: String,
    val imagem: Int,
    val fotoMetadata: PhotoMetadata? = null,
    val atribuicaoFoto: String = ""
)
