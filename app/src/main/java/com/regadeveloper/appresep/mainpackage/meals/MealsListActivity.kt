package com.regadeveloper.appresep.mainpackage.meals

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.regadeveloper.appresep.R
import com.regadeveloper.appresep.adapter.MealsAdapter
import com.regadeveloper.appresep.api.ApiConf
import com.regadeveloper.appresep.mainpackage.detail.DetailActivity
import com.regadeveloper.appresep.model.Meals
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MealsListActivity : AppCompatActivity(), MealsListView {
    private lateinit var daftarMealsPresenter: MealsList
    private lateinit var adapterMeals: MealsAdapter
    private val listMeals: MutableList<Meals> = mutableListOf()
    private var category: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meals_list)
        title = "Food List"

        ApiConf.getApi(this)
        daftarMealsPresenter = MealsList(this)

        val intent = intent
        intent?.let {
            category = it.getStringExtra("category")
        }

        swipeRefresh.post {
            loadData(category)
        }

        swipeRefresh.setOnRefreshListener {
            loadData(category)
        }
    }

    private fun loadData(category: String?) {
        daftarMealsPresenter.getMeals(category)
        adapterMeals = MealsAdapter(this, listMeals) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("id", it.idMeal)
            startActivity(intent)
        }
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapterMeals
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onResponse(list: ArrayList<Meals>?) {
        listMeals.clear()
        list?.let {
            listMeals.addAll(list)
        }
        adapterMeals.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }
}
