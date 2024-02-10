package com.treedevs.treemarkets.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.treedevs.treemarkets.R
import com.treedevs.treemarkets.databinding.ItemBinding
import com.treedevs.treemarkets.model.DataBarang

class AdapterBarang : RecyclerView.Adapter<AdapterBarang.ViewHolder>() {
    private var listData = ArrayList<DataBarang>()

    var onItemClick:((DataBarang)-> Unit)? = null

    fun setData(mListData: List<DataBarang>?){
        listData.clear()
        notifyDataSetChanged()
        if (mListData == null) return
        listData.addAll(mListData)
        notifyDataSetChanged()

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        LayoutInflater.from(parent.context).inflate(
            R.layout.item, parent, false))

    override fun onBindViewHolder(holder: AdapterBarang.ViewHolder, position: Int) {
        val data = listData[position]
        with(holder.binding) {
            barang.text = data.nama.capitalize()
            harga.text = "harga " +data.harga
            jumlah.text = "jumlah " +data.jumlah
            Glide.with(holder.itemView)
                .load("https://markets.treedevs.my.id/image/barang/${data.gambar}")
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Optional: Cache image
                .into(image)
        }
        holder.binding.carditem.setOnClickListener {
            onItemClick?.invoke(data)
        }
}
    override fun getItemCount(): Int = listData.size
    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val binding = ItemBinding.bind(itemView)

        init {
            binding.carditem.setOnLongClickListener {
                onItemClick?.invoke(listData[adapterPosition])

                return@setOnLongClickListener true
            }
        }
    }
}

