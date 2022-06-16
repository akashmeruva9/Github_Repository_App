package com.akash.meruva.github

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class recyclerviewadapter(var listener : MainActivity) : RecyclerView.Adapter<recyclerviewholder>() {

    val Allrepos = ArrayList<repo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): recyclerviewholder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item , parent , false)
        val viewholder = recyclerviewholder(view)

        view.setOnClickListener {
            listener.onitemclicked(Allrepos[viewholder.adapterPosition])
        }

        return viewholder
    }

    override fun onBindViewHolder(holder: recyclerviewholder, position: Int) {

        holder.repo_name.text = Allrepos[position].repo_name
        holder.user_name.text = Allrepos[position].user_name
        holder.des.text = Allrepos[position].descreption
        holder.language.text = Allrepos[position].language
        holder.date.text = Allrepos[position].datecreated
        Glide.with(holder.itemView.context).load(Allrepos[position].image_url).into(holder.image)
        holder.share.setOnClickListener {
            listener.share(Allrepos[position].share_url.toString())
        }
        holder.delete.setOnClickListener {
            listener.delete(Allrepos[position])
        }
    }

    override fun getItemCount(): Int {
        return Allrepos.size
    }

    fun updatelist(newlist: List<repo>)
    {
        Allrepos.clear()
        Allrepos.addAll(newlist)
        notifyDataSetChanged()
    }
}

class recyclerviewholder(itemview : View) : RecyclerView.ViewHolder(itemview)
{
    val repo_name = itemview.findViewById<TextView>(R.id.repositorytitle)
    val user_name = itemview.findViewById<TextView>(R.id.organisationname)
    val des = itemview.findViewById<TextView>(R.id.repositorydescrription)
    val share = itemview.findViewById<Button>(R.id.share_repo_button)
    val image = itemview.findViewById<ImageView>(R.id.profile_pic)
    val language = itemview.findViewById<TextView>(R.id.language_used)
    val date = itemview.findViewById<TextView>(R.id.date_created)
    val delete = itemview.findViewById<Button>(R.id.delete_repo_button)
}

interface shareinterface{
    fun share( url : String)
    fun onitemclicked( repo : repo)
    fun delete(repo: repo)
}



