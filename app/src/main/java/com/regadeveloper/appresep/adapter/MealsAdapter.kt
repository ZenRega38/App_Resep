package com.regadeveloper.appresep.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.regadeveloper.appresep.R
import com.regadeveloper.appresep.model.Meals
import kotlinx.android.synthetic.main.list_meals.view.*

class MealsAdapter(private val context: Context, private val list: List<Meals>, private val listener: (Meals) -> Unit)
    : RecyclerView.Adapter<MealsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int) =
        MealsAdapter.ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meals, p0, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bindItem(list[p1], listener, context)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(meals: Meals, listener: (Meals) -> Unit, context: Context) {
            Glide.with(context).load(meals.strMealThumb).into(itemView.iv_thumb)
            itemView.tv_meals.text = meals.strMeal
            itemView.setOnClickListener {
                listener(meals)
            }
        }
    }
}