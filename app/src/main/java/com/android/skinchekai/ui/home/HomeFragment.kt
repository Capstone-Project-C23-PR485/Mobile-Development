package com.android.skinchekai.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.skinchekai.R
import com.android.skinchekai.adapter.WawasanAdapter
import com.android.skinchekai.databinding.FragmentHomeBinding
import com.android.skinchekai.response.WawasanResponse

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<WawasanResponse>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
//        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addAll(getListWawasan())
        showRecyclerList()
    }

    private fun getListWawasan(): ArrayList<WawasanResponse> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listWawasan = ArrayList<WawasanResponse>()
        for (i in dataTitle.indices) {
            val wawasan = WawasanResponse(dataTitle[i], dataDescription[i])
            listWawasan.add(wawasan)
        }
        return listWawasan
    }

    private fun showRecyclerList() {
        binding.rvWawasan.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false)
        val wawasanAdapter = context?.let { WawasanAdapter(it,list) }
        binding.rvWawasan.adapter = wawasanAdapter
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}