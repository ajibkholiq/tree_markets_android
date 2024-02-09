package com.treedevs.treemarkets.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.treedevs.treemarkets.adapters.BarangAdapter
import com.treedevs.treemarkets.R
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HomeFragment : Fragment() {
    private val mainViewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        // Access RecyclerView from the inflated layout
        val recyclerView: RecyclerView = rootView.findViewById(R.id.itemsb)

        // Set layout manager and adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
          val adapter = BarangAdapter() // data is your list of items
        mainViewModel.barang.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        recyclerView.adapter = adapter
        return rootView
    }



//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
}