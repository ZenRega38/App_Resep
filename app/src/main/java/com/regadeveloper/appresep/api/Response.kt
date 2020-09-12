package com.regadeveloper.appresep.api

import com.google.gson.annotations.SerializedName
import com.regadeveloper.appresep.model.Category
import com.regadeveloper.appresep.model.Meals

data class Response (
    @SerializedName("categories")
    val categories: ArrayList<Category>?,

    @SerializedName("meals")
    val meals: ArrayList<Meals>?)
