package com.android.skinchekai.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.android.skinchekai.R
import com.android.skinchekai.databinding.FragmentHomeBinding
import com.android.skinchekai.databinding.FragmentProfileBinding
import com.android.skinchekai.response.DataProfile
import com.android.skinchekai.viewmodel.HomeViewModel
import com.android.skinchekai.viewmodel.ProfileViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        profileViewModel.profileResponse.observe(requireActivity()){
            setProfile(it)
        }
    }

    private fun setProfile(dataProfile: DataProfile?) {
        binding.tvName.text = dataProfile?.name
        if (dataProfile?.skinType.equals(null)){
            binding.tvSkinType.text = "Your skin type"
        }else {
            binding.tvSkinType.text = dataProfile?.skinType
        }
        if (dataProfile?.birthDate.equals(null)){
            binding.tvBirthday.text = "Your birthday"
        }else {
            binding.tvBirthday.text = dataProfile?.birthDate
        }
        binding.tvNameTop.text = dataProfile?.name
        binding.tvEmailTop.text = dataProfile?.email
        Glide.with(requireActivity())
            .load(dataProfile?.picture)
            .into(binding.profileImage)
    }
}