package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val listener: NewsItemsClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
      val viewHolder = NewsViewHolder(view)
      view.setOnClickListener{
         listener.onItemClicked(items[viewHolder.adapterPosition])
      }
      return viewHolder
    }


    override fun getItemCount(): Int {
        return items.size
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }


    fun updateNews(updatedNews: ArrayList<News>) {    //This function is used to update the list.
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()                        //when this function implement it calls 3 fns() of NewsListAdapter fns().
    }                                                 //1. onCreateViewHolder,2. getItemCount,3. onBindViewHolder
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){    //*itemView* is item_news.xml. It knows the items are here in item_news.xml.
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val author: TextView = itemView.findViewById(R.id.author)
}

interface NewsItemsClicked{
    fun onItemClicked(item: News)
}