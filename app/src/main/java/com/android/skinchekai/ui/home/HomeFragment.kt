package com.android.skinchekai.ui.home

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.skinchekai.R
import com.android.skinchekai.adapter.ProductAdapter
import com.android.skinchekai.adapter.WawasanAdapter
import com.android.skinchekai.databinding.FragmentHomeBinding
import com.android.skinchekai.response.DataItem
import com.android.skinchekai.response.WawasanResponse
import com.android.skinchekai.viewmodel.HomeViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<WawasanResponse>()
    private val homeViewModel: HomeViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.product.observe(requireActivity()){
            setProductList(it)
        }
        homeViewModel.isLoading.observe(requireActivity()){
            if (it){
                binding.progressBar2.visibility = View.VISIBLE
                binding.rvProduct.visibility = View.GONE
            }else {
                binding.progressBar2.visibility = View.GONE
                binding.rvProduct.visibility = View.VISIBLE
            }
        }
        list.addAll(getListWawasan())
        showRecyclerList()

    }
    private fun setProductList(dataItems: List<DataItem>?) {
        binding.rvProduct.layoutManager = LinearLayoutManager(requireContext())
        val adapter = dataItems?.let { ProductAdapter(it) }
        binding.rvProduct.adapter = adapter

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