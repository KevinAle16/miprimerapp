package com.example.kaftapp

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class spinnerAdapter(
    private val context: Context,
    private val groupid: Int,
    private val list:ArrayList<itemData>
): ArrayAdapter<itemData>(context,groupid,list){

    private val inflater:LayoutInflater=
        context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE) as
                LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getCustomView(position, parent)
    }

    private fun getCustomView(position: Int, parent: ViewGroup): View {
        val view = inflater.inflate(groupid, parent, false)
        val image = view.findViewById<ImageView>(R.id.imgCategoria)
        val lblCategoria = view.findViewById<TextView>(R.id.lblCategorias)
        val lblDescripcion = view.findViewById<TextView>(R.id.lblDescripcion)

        image.setImageResource(list[position].imageId)
        lblCategoria.text = list[position].txtCategoria
        lblDescripcion.text = list[position].txtDescripcion

        return view
    }
}