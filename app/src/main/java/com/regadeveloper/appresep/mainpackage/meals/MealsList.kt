package com.regadeveloper.appresep.mainpackage.meals

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.regadeveloper.appresep.api.Response

class MealsList(private val daftarMealsView: MealsListView) {
    fun getMeals(category: String?) {
        daftarMealsView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/filter.php?c={category}")
            .addPathParameter("category", category)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onResponse(response: Response?) {
                    response?.let {
                        daftarMealsView.onResponse(response.meals)
                    }
                    daftarMealsView.hideLoading()
                }

                override fun onError(anError: ANError?) {
                    daftarMealsView.onError(anError?.message)
                    daftarMealsView.hideLoading()
                }
            })
    }
}