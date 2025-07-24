package com.example.mycalc

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AdapterClass : RecyclerView.Adapter<AdapterClass.ModelViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdapterClass.ModelViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: AdapterClass.ModelViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class ModelViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}