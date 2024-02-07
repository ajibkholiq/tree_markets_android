package com.treedevs.treemarkets.model

data class BarangReponse<T>(

    val data: List<T>? = null,
    val message: String,
    val status: Boolean

)


