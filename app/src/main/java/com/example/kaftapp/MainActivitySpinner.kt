package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.core.widget.NestedScrollView
import androidx.appcompat.widget.AppCompatImageView

class MainActivitySpinner : AppCompatActivity() {

    private lateinit var spinnerBicicleta: Spinner
    private lateinit var cardViewBicycle: CardView
    private lateinit var imageViewBicycle: AppCompatImageView
    private lateinit var textViewBicycleType: TextView
    private lateinit var btnShowBicycle: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    private val bicicletas = arrayOf("Bicicleta de montaña", "Bicicleta de carretera", "Bicicleta BMX")
    private val imagenesBicicletas = arrayOf(
        R.drawable.mountain_bike_image,
        R.drawable.road_bike_image,
        R.drawable.bmx_bike_image
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main_spinner)

        iniciarComponentes()
        configurarSpinner()
        EventosBotones()
    }
   fun iniciarComponentes() {
        spinnerBicicleta = findViewById(R.id.spinnerBicicleta)
        cardViewBicycle = findViewById(R.id.cardViewBicycle)
        imageViewBicycle = findViewById(R.id.imageViewBicycle)
        textViewBicycleType = findViewById(R.id.textViewBicycleType)
        btnShowBicycle = findViewById(R.id.btnShowBicycle)
    }
    fun configurarSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bicicletas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBicicleta.adapter = adapter
    }

    @SuppressLint("DefaultLocale")
    fun EventosBotones() {
        btnShowBicycle.setOnClickListener{
            val selectedBike = spinnerBicicleta.selectedItemPosition
            if (selectedBike != -1) {
                imageViewBicycle.setImageResource(imagenesBicicletas[selectedBike])
                textViewBicycleType.text = bicicletas[selectedBike]
            } else {
                Toast.makeText(this, "Por favor selecciona una bicicleta", Toast.LENGTH_SHORT)
                    .show()
            }

            btnLimpiar.setOnClickListener {
                spinnerBicicleta.setSelection(0)
                imageViewBicycle.setImageResource(R.drawable.default_image)
                textViewBicycleType.text = "Tipo de bicicleta"
            }

            btnCerrar.setOnClickListener(View.OnClickListener {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("App IMC")
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


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}