package com.treedevs.treemarkets.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.treedevs.treemarkets.R
import com.treedevs.treemarkets.adapters.AdapterBarang
import com.treedevs.treemarkets.databinding.FragmentDashboardBinding
import com.treedevs.treemarkets.databinding.PopupBinding
import com.treedevs.treemarkets.ui.dashboard.DashboardViewModel.Companion.TAG

class DashboardFragment : Fragment() {
    private val mainViewModel: DashboardViewModel by viewModels()
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!
    //dialog
    var dialogBuilder: AlertDialog? = null
    private var dialogBinding : PopupBinding? = null
    private val bindingDialog get() = dialogBinding!!

    lateinit var uuid :String
    lateinit var barang :String
    lateinit var jumlah :String
    lateinit var harga :String
    lateinit var deskripsi:String

//    val binding: FragmentDashboardBinding = FragmentDashboardBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater,container,false)
        val rootView = binding.root

        // Access RecyclerView from the inflated layout
        val recyclerView: RecyclerView = rootView.findViewById(R.id.itemsb)

        // Set layout manager and adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterBarang() // data is your list of items
        binding.btnTambah.setOnClickListener{
            showdialog("new")
        }
        mainViewModel.barang.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        recyclerView.adapter = adapter

        adapter.onItemClick = {
            barang = it.nama
            jumlah = it.jumlah
            harga = it.harga
            deskripsi = it.deskripsi
            uuid = it.uuid
            showdialog("Edit")
        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        dialogBinding = null
        dialogBuilder?.dismiss() // Dismiss dialog to avoid potential window leaks
        dialogBuilder = null
    }

    private fun showdialog(flag :String){
        dialogBuilder = AlertDialog.Builder(requireContext()).create()
        dialogBinding = PopupBinding.inflate(layoutInflater)
        if (flag.equals("Edit")){
            bindingDialog.btnInput.visibility = View.GONE
            bindingDialog.btnDelete.visibility = View.VISIBLE
            bindingDialog.btnUpdate.visibility = View.VISIBLE
            bindingDialog.edtUuid.isEnabled = false
            bindingDialog.edtBarang.isFocusable = true

            bindingDialog.edtUuid.setText(uuid)
            bindingDialog.edtBarang.setText(barang)
            bindingDialog.edtJumlah.setText(jumlah)
            bindingDialog.edtHarga.setText(harga)
            bindingDialog.edtdeskripsi.setText(deskripsi)

        }else{
            bindingDialog.textuuid.visibility = View.GONE
            bindingDialog.edtUuid.visibility = View.GONE
            bindingDialog.btnInput.visibility = View.VISIBLE
            bindingDialog.btnDelete.visibility = View.GONE
            bindingDialog.btnUpdate.visibility = View.GONE
        }

      
        dialogBinding?.btnInput?.setOnClickListener {

            val iBarang = bindingDialog.edtBarang.text.toString()
            val iJumlah = bindingDialog.edtJumlah.text.toString()
            val iHarga = bindingDialog.edtHarga.text.toString()
            val iDeskripsi = bindingDialog.edtdeskripsi.text.toString()

            mainViewModel.postBarang(iBarang, iJumlah, iHarga, iDeskripsi)
            mainViewModel.getBarang()
            dialogBuilder?.dismiss()


        }

        dialogBinding?.btnUpdate?.setOnClickListener {
            val eUuid = uuid
            val iBarang = bindingDialog.edtBarang.text.toString()
            val iJumlah = bindingDialog.edtJumlah.text.toString()
            val iHarga = bindingDialog.edtHarga.text.toString()
            val iDeskripsi = bindingDialog.edtdeskripsi.text.toString()

            mainViewModel.editBarang(eUuid, iBarang, iHarga,iJumlah, iDeskripsi)
            mainViewModel.getBarang()
//            val adapter = AdapterBarang() // data is your list of items
//            mainViewModel.barang.observe(viewLifecycleOwner) {
//                adapter.setData(it)
//            }

            dialogBuilder?.dismiss()


        }

        dialogBinding?.btnDelete?.setOnClickListener {

            mainViewModel.deleteBarang(uuid)

            dialogBuilder?.dismiss()


        }


        dialogBuilder?.setView(bindingDialog.root)
        dialogBuilder?.show()

    }

}