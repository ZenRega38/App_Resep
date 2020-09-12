package com.regadeveloper.appresep.mainpackage.detail

import com.regadeveloper.appresep.model.Meals

interface DetailView {
    fun showLoading()
    fun onResponse(list: ArrayList<Meals>?)
    fun onError(message: String?)
    fun hideLoading()
}