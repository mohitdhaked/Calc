package com.example.mycalc

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListClassAdapter(private var list: List<ItemDetails>) : RecyclerView.Adapter<ListClassAdapter.ModelViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListClassAdapter.ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ModelViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListClassAdapter.ModelViewHolder, position: Int) {
        holder.txt1.text = list[position].name
        holder.txt2.text = list[position].desc
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txt1 = view.findViewById<TextView>(R.id.txt1)
        val txt2 = view.findViewById<TextView>(R.id.txt2)
    }
}