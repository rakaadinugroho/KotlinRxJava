package com.rakaadinugroho.kotlinrxjava.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rakaadinugroho.kotlinrxjava.R
import com.rakaadinugroho.kotlinrxjava.models.Data
import kotlinx.android.synthetic.main.row_news.view.*

/**
 * Created by Raka on 11/26/17.
 */
class NewsAdapter(private val newsList: List<Data>) : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    override fun onBindViewHolder(holder: NewsHolder?, position: Int) {
        holder!!.itemView.item_title.text = newsList.get(position).title
        holder.itemView.item_desc.text  = newsList.get(position).description
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsHolder {
        val view    = LayoutInflater.from(parent?.context).inflate(R.layout.row_news, parent, false)

        return NewsHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    class NewsHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
    }
}