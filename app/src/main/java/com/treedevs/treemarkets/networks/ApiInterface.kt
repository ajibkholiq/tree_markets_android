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
    @FormUrlEncoded
    @POST("barang")
    fun postBarang(
        @Field("nama") nama: String,
        @Field("kategori") kategori: String,
        @Field("jumlah") jumlah: String,
        @Field("harga") harga: String,
        @Field("deskripsi") deskripsi: String,

    ): Call<InputResponse>
    @FormUrlEncoded
    @PUT("barang/{uuid}")
    fun editBarang(
        @Path("uuid") uuid: String,
        @Field("nama") nama: String,
        @Field("kategori") kategori: String,
        @Field("harga") jumlah: String,
        @Field("jumlah") harga: String,
        @Field("deskripsi") deskrisi: String,

    ): Call<InputResponse>
//    @FormUrlEncoded
    @DELETE("barang/{uuid}")
//    @HTTP(method = "DELETE", path = "barang/{uuid}")
    fun deleteBarang(

        @Path("uuid") uuid : String?): Call<InputResponse>

}