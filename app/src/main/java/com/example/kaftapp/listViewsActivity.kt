package com.example.kaftapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class listViewsActivity : AppCompatActivity() {

    private lateinit var lstAlumnos: ListView
    private lateinit var txtSeleccionado: TextView
    private lateinit var btnCerrar: Button
    private lateinit var btnLimpiar: Button

    private lateinit var adapter: listViewsAdapter
    private lateinit var alumnos: ArrayList<alumnoData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_views)


        iniciarComponentes()
        eventosClick()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun iniciarComponentes() {
        lstAlumnos = findViewById(R.id.lstAlumnos)
        txtSeleccionado = findViewById(R.id.txtSeleccionado)
        btnCerrar = findViewById(R.id.btnCerrar)
        btnLimpiar = findViewById(R.id.btnLimpiar)

        alumnos = arrayListOf(
            alumnoData(
                getString(R.string.alumno1_nombre),
                getString(R.string.alumno1_carrera),
                getString(R.string.alumno1_matricula),
                R.drawable.alumno1
            ),
            alumnoData(
                getString(R.string.alumno2_nombre),
                getString(R.string.alumno2_carrera),
                getString(R.string.alumno2_matricula),
                R.drawable.alumno2
            ),
            alumnoData(
                getString(R.string.alumno3_nombre),
                getString(R.string.alumno3_carrera),
                getString(R.string.alumno3_matricula),
                R.drawable.alumno3
            ),
            alumnoData(
                getString(R.string.alumno4_nombre),
                getString(R.string.alumno4_carrera),
                getString(R.string.alumno4_matricula),
                R.drawable.alumno4
            ),
            alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            ),
            alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            ),
            alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            ),
            alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            ),
            alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            ),  alumnoData(
                getString(R.string.alumno5_nombre),
                getString(R.string.alumno5_carrera),
                getString(R.string.alumno5_matricula),
                R.drawable.alumno5
            )

        )

        adapter = listViewsAdapter(this, R.layout.listviews, alumnos)
        lstAlumnos.adapter = adapter

        lstAlumnos.setOnItemClickListener { _, _, position, _ ->
            val alumnoSeleccionado = alumnos[position]
            txtSeleccionado.text =  " ${alumnoSeleccionado.nombre}"
        }
    }

    private fun eventosClick() {
        btnCerrar.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
                .setTitle(getString(R.string.listado_alumnos))
                .setMessage(getString(R.string.confirmar_cierre))
                .setPositiveButton(getString(R.string.aceptar)) { _, _ -> finish() }
                .setNegativeButton(getString(R.string.cancelar)) { _, _ ->
                    Toast.makeText(this, getString(R.string.cancelado), Toast.LENGTH_SHORT).show()
                }
                .create()
            dialog.show()
        }

        btnLimpiar.setOnClickListener {
            adapter.notifyDataSetChanged()
            txtSeleccionado.setText("")
        }
    }

}