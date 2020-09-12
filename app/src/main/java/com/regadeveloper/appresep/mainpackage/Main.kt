package com.regadeveloper.appresep.mainpackage

import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.regadeveloper.appresep.api.Response

class Main(private val mainView: MainV) {
    fun getCategories() {
        mainView.showLoading()
        AndroidNetworking.get("https://www.themealdb.com/api/json/v1/1/categories.php")
            .setTag(this)
            .setPriority(Priority.LOW)
            .build()
            .getAsObject(Response::class.java, object : ParsedRequestListener<Response> {
                override fun onResponse(response: Response?) {
                    response?.let {
                        mainView.onResponse(it.categories)
                    }
                    mainView.hideLoading()
                }

                override fun onError(anError: ANError?) {
                    mainView.onError(anError?.message)
                }

            })
    }
}