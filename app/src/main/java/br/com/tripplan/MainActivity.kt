package br.com.tripplan

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import java.util.Calendar

class MainActivity : Activity() {

    private lateinit var btnDataPartida: Button
    private lateinit var btnDataRetorno: Button
    private lateinit var btnContinuar: Button

    private lateinit var edtDestino: EditText

    private lateinit var chkAventura: CheckBox
    private lateinit var chkCultura: CheckBox
    private lateinit var chkPraia: CheckBox

    private var dataPartida = ""
    private var dataRetorno = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtDestino = findViewById(R.id.edtDestino)

        btnDataPartida = findViewById(R.id.btnDataPartida)
        btnDataRetorno = findViewById(R.id.btnDataRetorno)
        btnContinuar = findViewById(R.id.btnContinuar)

        chkAventura = findViewById(R.id.chkAventura)
        chkCultura = findViewById(R.id.chkCultura)
        chkPraia = findViewById(R.id.chkPraia)

        btnDataPartida.setOnClickListener {
            abrirDatePicker(true)
        }

        btnDataRetorno.setOnClickListener {
            abrirDatePicker(false)
        }

        btnContinuar.setOnClickListener {
            continuarParaActivity2()
        }
    }

    private fun abrirDatePicker(isPartida: Boolean) {

        val calendario = Calendar.getInstance()

        val ano = calendario.get(Calendar.YEAR)
        val mes = calendario.get(Calendar.MONTH)
        val dia = calendario.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(
            this,
            { _, anoSelecionado, mesSelecionado, diaSelecionado ->

                val dataSelecionada =
                    "%02d/%02d/%04d".format(
                        diaSelecionado,
                        mesSelecionado + 1,
                        anoSelecionado
                    )

                if (isPartida) {
                    dataPartida = dataSelecionada
                    btnDataPartida.text = dataSelecionada
                } else {
                    dataRetorno = dataSelecionada
                    btnDataRetorno.text = dataSelecionada
                }
            },
            ano,
            mes,
            dia
        )

        datePicker.show()
    }

    private fun continuarParaActivity2() {

        val destino = edtDestino.text.toString()

        val preferencias = mutableListOf<String>()

        if (chkAventura.isChecked) {
            preferencias.add("Aventura")
        }

        if (chkCultura.isChecked) {
            preferencias.add("Cultura")
        }

        if (chkPraia.isChecked) {
            preferencias.add("Praia")
        }

        val preferenciasTexto = preferencias.joinToString(", ")

        println("=== TRANSIÇÃO PARA ACTIVITY 2 ===")
        println("Destino: $destino")
        println("Data de partida: $dataPartida")
        println("Data de retorno: $dataRetorno")
        println("Preferências: $preferenciasTexto")

        val intent = Intent(this, Activity2::class.java)

        intent.putExtra("destino", destino)
        intent.putExtra("dataPartida", dataPartida)
        intent.putExtra("dataRetorno", dataRetorno)
        intent.putExtra("preferencias", preferenciasTexto)

        startActivity(intent)
    }
}