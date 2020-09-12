package com.regadeveloper.appresep.mainpackage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.regadeveloper.appresep.R
import com.regadeveloper.appresep.adapter.CategoryAdapter
import com.regadeveloper.appresep.api.ApiConf
import com.regadeveloper.appresep.mainpackage.meals.MealsListActivity
import com.regadeveloper.appresep.model.Category
import com.smarteist.autoimageslider.SliderView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainV {
    private lateinit var mainPresenter: Main
    private lateinit var adapterCategories: CategoryAdapter
    private val listCategories: MutableList<Category> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ApiConf.getApi(this)
        mainPresenter = Main(this)
        setSliderImage()

        swipeRefresh.post {
            loadData()
        }

        swipeRefresh.setOnRefreshListener {
            loadData()
        }
    }

    private fun loadData() {
        mainPresenter.getCategories()
        adapterCategories = CategoryAdapter(this, listCategories) {
            val intent = Intent(this, MealsListActivity::class.java)
            intent.putExtra("category", it.strCategory)
            startActivity(intent)
        }
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapterCategories
    }

    override fun showLoading() {
        swipeRefresh.isRefreshing = true
    }

    override fun onResponse(list: ArrayList<Category>?) {
        listCategories.clear()
        list?.let {
            listCategories.addAll(list)
        }
        adapterCategories.notifyDataSetChanged()
    }

    override fun onError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideLoading() {
        swipeRefresh.isRefreshing = false
    }

    private fun setSliderImage() {
        for (i in 0..3) {
            val sliderView = SliderView(this)
            when (i) {
                0 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/sypxpx1515365095.jpg"
                }

                1 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/wrssvt1511556563.jpg"
                }

                2 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/uyqrrv1511553350.jpg"
                }

                3 -> {
                    sliderView.imageUrl = "https://www.themealdb.com/images/media/meals/1529444830.jpg"
                }
            }
            sliderView.setImageScaleType(ImageView.ScaleType.CENTER_CROP)
            //at last add this view in your layout :
            imageSlider.addSliderView(sliderView)
        }
    }
}
