package com.example.kaftapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.appcompat.widget.AppCompatImageView

class MainActivitySpinner : AppCompatActivity() {

    private lateinit var spinnerBicicleta: Spinner
    private lateinit var cardViewBicycle: CardView
    private lateinit var imageViewBicycle: AppCompatImageView
    private lateinit var textViewBicycleType: TextView
    private lateinit var btnShowBicycle: Button
    private lateinit var btnLimpiar: Button
    private lateinit var btnCerrar: Button

    public val descripcion_bicicletas by lazy { resources.getStringArray(R.array.descripcion_bicicletas) }
    public val bicicletas by lazy { resources.getStringArray(R.array.bicicletas) }
    public val imagenesBicicletas = arrayOf(
        R.drawable.default_image,
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
        eventosBotones()
    }
    @SuppressLint("DefaultLocale")
   fun iniciarComponentes() {
        spinnerBicicleta = findViewById(R.id.spinnerBicicleta)
        cardViewBicycle = findViewById(R.id.cardViewBicycle)
        imageViewBicycle = findViewById(R.id.imageViewBicycle)
        textViewBicycleType = findViewById(R.id.textViewBicycleType)
        btnShowBicycle = findViewById(R.id.btnShowBicycle)
       btnLimpiar = findViewById(R.id.btnLimpiar)
       btnCerrar = findViewById(R.id.btnCerrar)
        imageViewBicycle.setImageResource(R.drawable.default_image) // Mostrar la imagen por defecto al arrancar
    }
    @SuppressLint("DefaultLocale")
    fun configurarSpinner() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, bicicletas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerBicicleta.adapter = adapter
    }

    @SuppressLint("DefaultLocale")
    fun eventosBotones() {
        btnShowBicycle.setOnClickListener{
            val selectedBike = spinnerBicicleta.selectedItemPosition
            if (selectedBike == 0) {
                Toast.makeText(this, getString(R.string.seleccioneBici), Toast.LENGTH_SHORT).show()
                imageViewBicycle.setImageResource(R.drawable.default_image)
                textViewBicycleType.text = getString(R.string.tipoBici)
            } else {
                imageViewBicycle.setImageResource(imagenesBicicletas[selectedBike])
                textViewBicycleType.text = bicicletas[selectedBike]

                val builder = AlertDialog.Builder(this)
                builder.setTitle(bicicletas[selectedBike])
                builder.setMessage(descripcion_bicicletas[selectedBike])
                builder.setPositiveButton(getString(R.string.aceptar), null)
                builder.show()
            }

            btnLimpiar.setOnClickListener {
                spinnerBicicleta.setSelection(0)
                imageViewBicycle.setImageResource(R.drawable.default_image)
                textViewBicycleType.text = getString(R.string.tipoBici)
            }
    btnCerrar.setOnClickListener {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle(getString(R.string.Cliente_app))
        dialog.setMessage(getString(R.string.confirmar_cierre))
        dialog.setPositiveButton(getString(R.string.aceptar)) { _, _ ->
            finish()
        }
        dialog.setNegativeButton(getString(R.string.cancelar)) { _, _ ->
            Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
        }
        dialog.show()
    }
}
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}