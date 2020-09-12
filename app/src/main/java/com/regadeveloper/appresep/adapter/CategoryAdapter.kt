package com.regadeveloper.appresep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.regadeveloper.appresep.R
import com.regadeveloper.appresep.model.Category
import kotlinx.android.synthetic.main.list_category.view.*

class CategoryAdapter(private val context: Context, private val list: List<Category>, private val listener: (Category) -> Unit)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        CategoryAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_category, p0, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list[p1], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(categories: Category, listener: (Category) -> Unit, context: Context) {
            Glide.with(context).load(categories.strCategoryThumb).into(itemView.iv_image)
            itemView.tv_category.text = categories.strCategory
            itemView.setOnClickListener {
                listener(categories)
            }
        }
    }
}