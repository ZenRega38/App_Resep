package com.regadeveloper.appresep.mainpackage.meals

import com.regadeveloper.appresep.model.Meals

interface MealsListView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}