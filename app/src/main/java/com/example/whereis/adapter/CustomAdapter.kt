package com.example.whereis.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.whereis.R
import com.example.whereis.model.Terremoto
import kotlinx.android.synthetic.main.template.view.*
import org.w3c.dom.Text

class CustomAdapter(var context: Context, items: ArrayList<Terremoto>) : BaseAdapter() {

    var items: ArrayList<Terremoto>? = null

    init {
        this.items = items
    }

    override fun getCount(): Int {
        return items?.count()!!
    }

    override fun getItem(position: Int): Any {
        return items?.get(position)!!
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var holder: ViewHolder? = null
        var vista: View? = convertView

        if(vista == null) {
            vista = LayoutInflater.from(context).inflate(R.layout.template, null)
            holder = ViewHolder(vista)
            vista.tag = holder
        }else {
            holder = vista.tag as? ViewHolder
        }
        val item = getItem(position) as Terremoto
        holder?.title?.text = item.title
        holder?.place?.text = "Magnitud:" + item.mag
        return vista!!
    }

    private class ViewHolder(view: View) {
        var title:TextView? = null
        var place: TextView? = null

        init {
            this.title = view.findViewById(R.id.titleTerremoto)
            this.place = view.findViewById(R.id.magTerremoto)
        }
    }
}