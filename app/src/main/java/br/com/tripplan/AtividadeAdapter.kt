package br.com.tripplan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AtividadeAdapter(
    private val atividades: List<Atividade>,
    private val onSelecionar: (Atividade) -> Unit
) : RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtividadeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_atividade, parent, false)

        return AtividadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AtividadeViewHolder, position: Int) {
        holder.bind(atividades[position])
    }

    override fun getItemCount(): Int = atividades.size

    inner class AtividadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAtividade: ImageView = itemView.findViewById(R.id.imgAtividade)
        private val txtNomeAtividade: TextView = itemView.findViewById(R.id.txtNomeAtividade)
        private val txtDescricaoAtividade: TextView =
            itemView.findViewById(R.id.txtDescricaoAtividade)
        private val txtCategoriaAtividade: TextView =
            itemView.findViewById(R.id.txtCategoriaAtividade)
        private val txtDetalhesAtividade: TextView =
            itemView.findViewById(R.id.txtDetalhesAtividade)
        private val btnSelecionarAtividade: Button =
            itemView.findViewById(R.id.btnSelecionarAtividade)

        fun bind(atividade: Atividade) {
            imgAtividade.setImageResource(atividade.imagem)
            txtNomeAtividade.text = atividade.nome
            txtDescricaoAtividade.text = atividade.descricao
            txtCategoriaAtividade.text = atividade.categoria
            txtDetalhesAtividade.text =
                "Duração: ${atividade.duracao} | Dificuldade: ${atividade.dificuldade}"

            btnSelecionarAtividade.setOnClickListener {
                onSelecionar(atividade)
            }
        }
    }
}
