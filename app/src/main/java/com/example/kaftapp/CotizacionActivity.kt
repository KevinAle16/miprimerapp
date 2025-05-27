package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import kotlin.math.abs

class CotizacionActivity : AppCompatActivity() {

    private lateinit var txtCliente: TextView
    private lateinit var txtFolio: TextView
    private lateinit var txtDescripcion: EditText
    private lateinit var txtPorcentaje: EditText
    private lateinit var txtPrecio: EditText

    private lateinit var rdb12: RadioButton
    private lateinit var rdb24: RadioButton
    private lateinit var rdb36: RadioButton
    private lateinit var rdb48: RadioButton

    private lateinit var txtPagoInicial: TextView
    private lateinit var txtTotalFin: TextView
    private lateinit var txtPagoMensual: TextView

    private lateinit var btnCalcular: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_cotizacion)

        iniciarComponentes()
        eventosClic()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

        @SuppressLint("SetTextI18n")
        fun iniciarComponentes() {
            txtCliente = findViewById(R.id.txtCliente)
            txtFolio = findViewById(R.id.txtFolio)
            txtDescripcion = findViewById(R.id.txtDescripcion)
            txtPorcentaje = findViewById(R.id.txtPorcentaje)
            txtPrecio = findViewById(R.id.txtPrecio)

            rdb12 = findViewById(R.id.rdb12)
            rdb24 = findViewById(R.id.rdb24)
            rdb36 = findViewById(R.id.rdb36)
            rdb48 = findViewById(R.id.rdb48)

            txtPagoInicial = findViewById(R.id.txtPagoInicial)
            txtTotalFin = findViewById(R.id.txtTotalFin)
            txtPagoMensual = findViewById(R.id.txtPagoMensual)

            btnCalcular = findViewById(R.id.btnCalcular)
            btnLimpiar = findViewById(R.id.btnLimpiar)
            btnCerrar = findViewById(R.id.btnCerrar)

            val strCliente = intent.getStringExtra("cliente").toString()
            txtCliente.text = strCliente

            val folio = abs(Cotizacion().generaFolio())
            txtFolio.text = "Folio: $folio"
        }

        @SuppressLint("SetTextI18n")
        fun eventosClic() {
            btnCalcular.setOnClickListener {
                val cotizacion = Cotizacion()

                if (txtCliente.text.isEmpty() || txtDescripcion.text.isEmpty() || txtPrecio.text.isEmpty() || txtPorcentaje.text.isEmpty()) {
                    Toast.makeText(this, "Falta capturar algún dato", Toast.LENGTH_SHORT).show()
                } else {
                    cotizacion.numCotizacion = Cotizacion().generaFolio()
                    cotizacion.descripcion = txtDescripcion.text.toString()
                    cotizacion.precio = txtPrecio.text.toString().toFloat()
                    cotizacion.porPagInicial = txtPorcentaje.text.toString().toFloat()

                    cotizacion.plazos = when {
                        rdb12.isChecked -> 12
                        rdb24.isChecked -> 24
                        rdb36.isChecked -> 36
                        rdb48.isChecked -> 48
                        else -> 12
                    }

                    txtPagoInicial.text = getString(R.string.pinicial) + ": " + cotizacion.calcularPagoInicial().toString() + ":"
                    txtTotalFin.text = getString(R.string.tfin)+": " + cotizacion.calcularTotalFin().toString()
                    txtPagoMensual.text = getString(R.string.pmensual)+ ": " + cotizacion.calcularPagoMensual().toString()
                }
            }

            btnLimpiar.setOnClickListener {
                txtPagoInicial.text = getString(R.string.pinicial)
                txtTotalFin.text = getString(R.string.tfin)
                txtPagoMensual.text = getString(R.string.pmensual)
                txtDescripcion.setText("")
                txtPrecio.setText("")
                txtPorcentaje.setText("")
                rdb12.isChecked = true
            }

            btnCerrar.setOnClickListener {
                finish()
            }
        }



}