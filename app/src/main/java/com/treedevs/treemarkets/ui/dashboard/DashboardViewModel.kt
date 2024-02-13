package com.treedevs.treemarkets.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.treedevs.treemarkets.model.BarangReponse
import com.treedevs.treemarkets.model.DataBarang
import com.treedevs.treemarkets.model.InputResponse
import com.treedevs.treemarkets.networks.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DashboardViewModel : ViewModel() {
    private val _barang = MutableLiveData<List<DataBarang>?>()
    val barang: MutableLiveData<List<DataBarang>?> = _barang

//    private val _snackbarText = MutableLiveData<Event<String>>()
//    val snackbarText: LiveData<Event<String>> = _snackbarText

    private val _action = MutableLiveData<Boolean>()
    val action: LiveData<Boolean> = _action

    companion object{
        const val TAG = "BarangViewModel"
    }

    init {
        getBarang()
    }

    fun getBarang(){

        val client = ApiConfig.getApiService().getAllBarang()
        client.enqueue(object : Callback<BarangReponse<DataBarang>> {
            override fun onResponse(call: Call<BarangReponse<DataBarang>>, response: Response<BarangReponse<DataBarang>>) {

                if (response.isSuccessful){

                    val resp =response.body()
                    _barang.value = resp?.data!!
                    Log.e(TAG, "onFailure: ${response.body()?.message.toString()}")

                }else{
                    Log.e(TAG, "onFailure: ${response.body()?.message.toString()}")
                }
            }

            override fun onFailure(call: Call<BarangReponse<DataBarang>>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }

        })
    }
    fun postBarang(nama: String, jumlah: String, harga :String, deskripsi : String){
        val client = ApiConfig.getApiService().postBarang(nama,"1", jumlah, harga, deskripsi)
        client.enqueue(object : Callback<InputResponse>{
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {
                if (response.isSuccessful){

//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status

                }else{
//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status
                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
//                _snackbarText.value = Event(t.message.toString())
            }

        })

    }
    fun editBarang(uuid: String ,nama: String,  harga :String, jumlah: String, deskripsi : String){
        val client = ApiConfig.getApiService().editBarang(uuid,nama,"1" ,harga,jumlah ,deskripsi)
        client.enqueue(object : Callback<InputResponse>{
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {
                if (response.isSuccessful){

//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status

                }else{
//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status
                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
//                _snackbarText.value = Event(t.message.toString())
            }

        })

    }
    fun deleteBarang(uuid: String){
        val client = ApiConfig.getApiService().deleteBarang(uuid)
        client.enqueue(object : Callback<InputResponse>{
            override fun onResponse(call: Call<InputResponse>, response: Response<InputResponse>) {
                if (response.isSuccessful){

//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status

                }else{
//                    _snackbarText.value = Event(response.body()?.message.toString())
                    _action.value = response.body()?.status
                }
            }

            override fun onFailure(call: Call<InputResponse>, t: Throwable) {
//                _snackbarText.value = Event(t.message.toString())
            }

        })

    }

}