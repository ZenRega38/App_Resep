package com.regadeveloper.appresep.model

import com.google.gson.annotations.SerializedName

class Category (
    @SerializedName("idCategory")
    val idCategory: String? = null,

    @SerializedName("strCategory")
    val strCategory: String? = null,

    @SerializedName("strCategoryThumb")
    val strCategoryThumb: String? = null,

    @SerializedName("strCategoryDescription")
    val strCategoryDescription: String? = null)
