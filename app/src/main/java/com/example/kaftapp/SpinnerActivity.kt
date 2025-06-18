package com.example.kaftapp

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*
import androidx.appcompat.app.AlertDialog

class SpinnerActivity : AppCompatActivity() {

    private lateinit var txtSeleccion: TextView
    private lateinit var spnItem: Spinner
    private lateinit var btnLimpiar: Button
    private lateinit var btnSalir: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_spinner)

            txtSeleccion = findViewById(R.id.txtSeleccion)
            spnItem = findViewById(R.id.spnItem)
            btnLimpiar = findViewById(R.id.btnLimpiar)
            btnSalir = findViewById(R.id.btnSalir)

            val list = arrayListOf(
                itemData("", getString(R.string.msgDescripcion), 0),
                itemData(
                    getString(R.string.itemAgradecimiento),
                    getString(R.string.msgAgradecimiento),
                    R.drawable.agradecimiento
                ),
                itemData(
                    getString(R.string.itemAmor),
                    getString(R.string.msgAmor),
                    R.drawable.corazon
                ),
                itemData(
                    getString(R.string.itemNewYear),
                    getString(R.string.msgNewYear),
                    R.drawable.nuevo
                ),
                itemData(
                    getString(R.string.itemCanciones),
                    getString(R.string.msgCanciones),
                    R.drawable.canciones)
            )


            val adapter = spinnerAdapter(this, R.layout.spinner, list)
            spnItem.adapter = adapter

            spnItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: android.view.View,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        txtSeleccion.text = ""
                    } else {
                        val item = list[position]
                        txtSeleccion.text =
                            "${getString(R.string.msgSeleccion)}: ${item.txtCategoria}"
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    txtSeleccion.text = ""
                }
            }

            btnLimpiar.setOnClickListener(View.OnClickListener  {
                spnItem.setSelection(0)
                txtSeleccion.text = ""
            })

            btnSalir.setOnClickListener {
                val dialog = AlertDialog.Builder(this)
                dialog.setTitle(getString(R.string.spinner_name))
                dialog.setMessage(getString(R.string.confirmar_cierre))
                dialog.setPositiveButton(getString(R.string.aceptar)) { _, _ ->
                    finish()
                }
                dialog.setNegativeButton(getString(R.string.cancelar)) { _, _ ->
                    Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
                }
                .show()
            }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
            }
    }
}