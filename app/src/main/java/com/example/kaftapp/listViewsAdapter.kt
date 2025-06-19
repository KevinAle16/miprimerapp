package com.example.kaftapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class listViewsAdapter(

    private var context: Context,
    private var groupid: Int,
    private var list: ArrayList<alumnoData>

): ArrayAdapter<alumnoData>(context, groupid, list) {
    private val inflater: LayoutInflater =
        context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {

        return getCustomView(position, parent)
    }

    private fun getCustomView(position: Int, parent: ViewGroup): View {
        val view = inflater.inflate(groupid, parent, false)

        val imgFoto = view.findViewById<ImageView>(R.id.imgFoto)
        val txtNombre = view.findViewById<TextView>(R.id.txtNombre)
        val txtCarrera = view.findViewById<TextView>(R.id.txtCarrera)
        val txtMatricula = view.findViewById<TextView>(R.id.txtMatricula)

        val alumno = list[position]
        imgFoto.setImageResource(alumno.fotoId)
        txtNombre.text = alumno.nombre
        txtCarrera.text = alumno.carrera
        txtMatricula.text = alumno.matricula

        return view
    }
}