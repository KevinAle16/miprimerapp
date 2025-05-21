package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityTemperature : AppCompatActivity() {

    private lateinit var txtValor: EditText
    private lateinit var spinnerUnidadOrigen: Spinner
    private lateinit var spinnerUnidadDestino: Spinner
    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button
    private lateinit var lblResultado: TextView
    private val unidades = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_temperatura)

        iniciarComponentes()
        configureSpinners()
        eventosBotones()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
        fun iniciarComponentes() {
            txtValor = findViewById(R.id.txtValor)
            spinnerUnidadOrigen = findViewById(R.id.spinnerUnidadOrigen)
            spinnerUnidadDestino = findViewById(R.id.spinnerUnidadDestino)
            btnCalcular = findViewById(R.id.btnCalcular)
            btnLimpiar = findViewById(R.id.btnLimpiar)
            btnCerrar = findViewById(R.id.btnCerrar)
            lblResultado = findViewById(R.id.lblResultado)
        }

        fun configureSpinners() {
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, unidades)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerUnidadOrigen.adapter = adapter
            spinnerUnidadDestino.adapter = adapter
        }

        fun converterTemperature(valor: Double, origen: String, destino: String): Double {
            return when (origen to destino) {
                "Celsius" to "Fahrenheit" -> valor * 9 / 5 + 32
                "Celsius" to "Kelvin" -> valor + 273.15
                "Fahrenheit" to "Celsius" -> (valor - 32) * 5 / 9
                "Fahrenheit" to "Kelvin" -> (valor - 32) * 5 / 9 + 273.15
                "Kelvin" to "Celsius" -> valor - 273.15
                "Kelvin" to "Fahrenheit" -> (valor - 273.15) * 9 / 5 + 32
                else -> valor
            }
        }

        @SuppressLint("DefaultLocale")
        fun eventosBotones() {
            btnCalcular.setOnClickListener {
                val valorStr = txtValor.text.toString()
                val origen = spinnerUnidadOrigen.selectedItem.toString()
                val destino = spinnerUnidadDestino.selectedItem.toString()

                val valor = valorStr.toDoubleOrNull()
                if (valor == null) {
                    Toast.makeText(
                        this,
                        getString(R.string.error_valor_invalido),
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                if (origen == destino) {
                    lblResultado.text = getString(R.string.mismo_unidad)
                    return@setOnClickListener
                }
                val resultado = converterTemperature(valor, origen, destino)
                lblResultado.text =
                    String.format(getString(R.string.resultado_conversion), resultado, destino)


                btnLimpiar.setOnClickListener(View.OnClickListener {
                    txtValor.setText("")
                    spinnerUnidadOrigen.setSelection(0)
                    spinnerUnidadDestino.setSelection(1)
                    lblResultado.text = getString(R.string.mensaje_resultado_conversion)
                })
            }
                btnCerrar.setOnClickListener (View.OnClickListener{
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("App Conversion Temperatura")
                    builder.setMessage("¿Desea Cerrar la aplicacion?")

                    builder.setPositiveButton("Aceptar") { _, _ ->
                        finish()
                    }
                    builder.setNeutralButton("cancelar") { _, _ ->
                        Toast.makeText(applicationContext, "cancelado", Toast.LENGTH_SHORT).show()
                    }
                    builder.setNeutralButton("Quiza") { _, _ ->
                        Toast.makeText(applicationContext, "Quiza", Toast.LENGTH_SHORT).show()
                    }
                    builder.show()
                })
        }
}

