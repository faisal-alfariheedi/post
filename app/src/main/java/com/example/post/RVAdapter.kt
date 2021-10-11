package com.example.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class RVAdapter(private val rv: ArrayList<dat.People>): RecyclerView.Adapter<RVAdapter.ItemViewHolder>()  {
    class ItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVAdapter.ItemViewHolder {
        return RVAdapter.ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.rvlist,parent,false )
        )
    }

    override fun onBindViewHolder(holder: RVAdapter.ItemViewHolder, position: Int) {
        val rvv = rv[position]

        holder.itemView.apply {
            var rvlisting= findViewById<TextView>(R.id.rvlisting)
            var rvlisting2=findViewById<TextView>(R.id.rvlisting2)
            rvlisting.text = rvv.name
            rvlisting2.text = rvv.location
            findViewById<TextView>(R.id.ID).text= rvv.pk.toString()


        }
    }

    override fun getItemCount() = rv.size
}
