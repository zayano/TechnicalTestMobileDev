package com.example.technicaltestmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.text.SimpleDateFormat

class TopHeadlinesAdapter (val data: List<ArticlesItem>?) :
    RecyclerView.Adapter<TopHeadlinesAdapter.MyHolder>() {

    var listener: RecyclerViewClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_top_headlines,
            parent, false)
        return MyHolder(v)
    }
    override fun getItemCount(): Int = data?.size ?: 0
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position), listener)

    }
    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: ArticlesItem?, listener: RecyclerViewClickListener?) {
            val title: TextView = itemView.findViewById(R.id.titleHeadlines)
            val author: TextView = itemView.findViewById(R.id.authorHeadlines)
            val date: TextView = itemView.findViewById(R.id.dateHeadlines)
            val image: ImageView = itemView.findViewById(R.id.imageHeadlines)

            val parser = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
            val formatter = SimpleDateFormat("dd/MM/yyyy")
            val dateFormatted: String = formatter.format(parser.parse(get?.publishedAt))

            title.text = get?.title
            author.text = get?.author
            date.text = dateFormatted
            Glide.with(itemView).load(get?.urlToImage)
                .fallback(com.google.android.material.R.drawable.mtrl_ic_error)
                .error(com.google.android.material.R.drawable.mtrl_ic_error)
                .into(image)

            itemView.setOnClickListener() {
                listener?.onItemClicked(it, get)
            }
        }
    }
}

interface RecyclerViewClickListener {
    // method yang akan dipanggil di MainActivity
    fun onItemClicked(view: View, article: ArticlesItem?)
}