package br.com.tripplan

import android.net.Uri
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.libraries.places.api.model.PhotoMetadata
import com.google.android.libraries.places.api.net.FetchResolvedPhotoUriRequest
import com.google.android.libraries.places.api.net.PlacesClient

class AtividadeAdapter(
    private val atividades: List<Atividade>,
    private val onSelecionar: (Atividade) -> Unit,
    private val placesClient: PlacesClient? = null
) : RecyclerView.Adapter<AtividadeAdapter.AtividadeViewHolder>() {

    private val fotoUris = mutableMapOf<PhotoMetadata, Uri?>()
    private val fotosEmCarregamento = mutableSetOf<PhotoMetadata>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AtividadeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_atividade, parent, false)

        return AtividadeViewHolder(view)
    }

    override fun onBindViewHolder(holder: AtividadeViewHolder, position: Int) {
        holder.bind(atividades[position])
    }

    override fun getItemCount(): Int = atividades.size

    override fun onViewRecycled(holder: AtividadeViewHolder) {
        holder.limparImagem()
        super.onViewRecycled(holder)
    }

    inner class AtividadeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imgAtividade: ImageView = itemView.findViewById(R.id.imgAtividade)
        private val txtNomeAtividade: TextView = itemView.findViewById(R.id.txtNomeAtividade)
        private val txtDescricaoAtividade: TextView =
            itemView.findViewById(R.id.txtDescricaoAtividade)
        private val txtCategoriaAtividade: TextView =
            itemView.findViewById(R.id.txtCategoriaAtividade)
        private val txtDetalhesAtividade: TextView =
            itemView.findViewById(R.id.txtDetalhesAtividade)
        private val txtAtribuicaoFoto: TextView =
            itemView.findViewById(R.id.txtAtribuicaoFoto)
        private val btnSelecionarAtividade: Button =
            itemView.findViewById(R.id.btnSelecionarAtividade)

        fun bind(atividade: Atividade) {
            Glide.with(itemView).clear(imgAtividade)
            imgAtividade.setImageResource(atividade.imagem)
            txtNomeAtividade.text = atividade.nome
            txtDescricaoAtividade.text = atividade.descricao
            txtCategoriaAtividade.text = atividade.categoria
            txtDetalhesAtividade.text =
                "Duração: ${atividade.duracao} | Dificuldade: ${atividade.dificuldade}"
            txtAtribuicaoFoto.text = Html.fromHtml(
                atividade.atribuicaoFoto,
                Html.FROM_HTML_MODE_COMPACT
            )
            txtAtribuicaoFoto.visibility = if (atividade.atribuicaoFoto.isBlank()) {
                View.GONE
            } else {
                View.VISIBLE
            }

            carregarImagem(atividade)

            btnSelecionarAtividade.setOnClickListener {
                onSelecionar(atividade)
            }
        }

        fun limparImagem() {
            Glide.with(itemView).clear(imgAtividade)
        }

        private fun carregarImagem(atividade: Atividade) {
            val fotoMetadata = atividade.fotoMetadata ?: return
            val client = placesClient ?: return

            if (fotoUris.containsKey(fotoMetadata)) {
                fotoUris[fotoMetadata]?.let { uri ->
                    Glide.with(itemView)
                        .load(uri)
                        .centerCrop()
                        .placeholder(atividade.imagem)
                        .error(atividade.imagem)
                        .into(imgAtividade)
                }
                return
            }

            if (!fotosEmCarregamento.add(fotoMetadata)) {
                return
            }

            val request = FetchResolvedPhotoUriRequest.builder(fotoMetadata)
                .setMaxWidth(800)
                .setMaxHeight(450)
                .build()

            client.fetchResolvedPhotoUri(request)
                .addOnSuccessListener { response ->
                    fotoUris[fotoMetadata] = response.uri
                    fotosEmCarregamento.remove(fotoMetadata)
                    notificarItensComFoto(fotoMetadata)
                }
                .addOnFailureListener { error ->
                    fotoUris[fotoMetadata] = null
                    fotosEmCarregamento.remove(fotoMetadata)
                    Log.w("AtividadeAdapter", "Não foi possível carregar a foto", error)
                }
        }
    }

    private fun notificarItensComFoto(fotoMetadata: PhotoMetadata) {
        atividades.forEachIndexed { index, atividade ->
            if (atividade.fotoMetadata == fotoMetadata) {
                notifyItemChanged(index)
            }
        }
    }
}
