package com.regadeveloper.appresep.mainpackage

import com.regadeveloper.appresep.model.Category

interface MainV {
    fun showLoading()
    fun onResponse(list: ArrayList<Category>?)
    fun onError(message: String?)
    fun hideLoading()
}