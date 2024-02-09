package com.treedevs.treemarkets.networks

import com.treedevs.treemarkets.model.BarangReponse
import com.treedevs.treemarkets.model.DataBarang
import com.treedevs.treemarkets.model.InputResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @GET("getbarang")
    fun getBarang(): Call<BarangReponse<DataBarang>>
    @GET("barang")
    fun getAllBarang(): Call<BarangReponse<DataBarang>>
    @Multipart
    @POST("barang")
    fun postBarang(
        @Path("uuid") uuid: String,

    ): Call<InputResponse>
    @Multipart
    @PUT("barang/{uuid}")
    fun editBarang(
        @Path("uuid") uuid: String,

    ): Call<InputResponse>
    @FormUrlEncoded
    @HTTP(method = "DELETE", path = "Delete_mahasiswa/{uuid}", hasBody = true)
    fun deleteMahasiswa(@Path("uuid") uuid : String?): Call<InputResponse>

}