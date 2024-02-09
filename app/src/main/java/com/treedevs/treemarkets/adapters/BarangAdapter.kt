package com.treedevs.treemarkets.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.treedevs.treemarkets.model.DataBarang
import com.treedevs.treemarkets.R
import com.treedevs.treemarkets.databinding.BarangItemBinding

class BarangAdapter : RecyclerView.Adapter<BarangAdapter.ViewHolder>(){

    private var listData = ArrayList<DataBarang>()

    var onItemClick:((DataBarang)-> Unit)? = null

    fun setData(mListData: List<DataBarang>?){
        listData.clear()
        notifyDataSetChanged()
        if (mListData == null) return
        listData.addAll(mListData)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
        R.layout.barang_item, parent, false))

    override fun onBindViewHolder(holder: BarangAdapter.ViewHolder, position: Int) {
        val data = listData[position]
        with(holder.binding) {
            barang.text = data.nama.capitalize()
            harga.text = "harga "+data.harga
            Glide.with(holder.itemView)
                .load("https://markets.treedevs.my.id/image/barang/${data.gambar}")
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: Cache image
                .into(image)
        }

    }

    override fun getItemCount(): Int = listData.size




    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val binding = BarangItemBinding.bind(itemView)

//    fun bind(data: DataBarang){
//
//        with(binding){
//            barang.text = data.nama
//            harga.text = data.harga
//            Glide.with(holder.itemView)
//                .load("https://markets.treedevs.my.id/image/barang/"+data.image)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: Cache image
//                .into(holder.imageView)
//        }
//    }

    init {
        binding.carditem.setOnLongClickListener {
            onItemClick?.invoke(listData[adapterPosition])

            return@setOnLongClickListener true
        }
    }
}
}