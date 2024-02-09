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
    var dialogBuilder: AlertDialog? = null

    private var dialogBinding : PopupBinding? = null
    private val bindingDialog get() = dialogBinding!!
//    val binding: FragmentDashboardBinding = FragmentDashboardBinding.inflate(layoutInflater)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)

        // Access RecyclerView from the inflated layout
        val recyclerView: RecyclerView = rootView.findViewById(R.id.itemsb)

        // Set layout manager and adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = AdapterBarang() // data is your list of items
//        binding.btnTambah.setOnClickListener{
//            showdialog()
//        }
        mainViewModel.barang.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        recyclerView.adapter = adapter

        adapter.onItemClick = {

        }
        return rootView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showdialog(){
        dialogBuilder = AlertDialog.Builder(requireContext()).create()
        dialogBinding = PopupBinding.inflate(layoutInflater)
        dialogBuilder?.setView(bindingDialog.root)
        dialogBuilder?.show()

    }

}