package com.regadeveloper.appresep.mainpackage.detail

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.regadeveloper.appresep.api.Response

class Detail(private val detailMealsView: DetailView) {
    fun getDetail(id: String?) {
        detailMealsView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/lookup.php?i={id}")
            .addPathParameter("id", id)
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onResponse(response: Response?) {
                    response?.let {
                        detailMealsView.onResponse(it.meals)
                    }
                    detailMealsView.hideLoading()
                }

                override fun onError(anError: ANError?) {
                    detailMealsView.onError(anError?.message)
                    detailMealsView.hideLoading()
                }
            })
    }
}