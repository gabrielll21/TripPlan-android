package br.com.tripplan

import android.app.Activity
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import java.util.Calendar

class MainActivity : Activity() {

    private lateinit var btnDataPartida: Button
    private lateinit var btnDataRetorno: Button

    private var dataPartida = ""
    private var dataRetorno = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDataPartida = findViewById(R.id.btnDataPartida)
        btnDataRetorno = findViewById(R.id.btnDataRetorno)

        btnDataPartida.setOnClickListener {
            abrirDatePicker(true)
        }

        btnDataRetorno.setOnClickListener {
            abrirDatePicker(false)
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
}