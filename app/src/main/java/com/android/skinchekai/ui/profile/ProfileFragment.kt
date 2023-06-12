package com.android.skinchekai.ui.profile

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.android.skinchekai.R
import com.android.skinchekai.databinding.BottomSheetDialogLayoutBinding
import com.android.skinchekai.databinding.BottomSheetDialogLayoutProfileBinding
import com.android.skinchekai.databinding.FragmentHomeBinding
import com.android.skinchekai.databinding.FragmentProfileBinding
import com.android.skinchekai.response.DataProfile
import com.android.skinchekai.viewmodel.HomeViewModel
import com.android.skinchekai.viewmodel.ProfileViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var bottomSheetBinding : BottomSheetDialogLayoutProfileBinding
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

        binding.imgEditProfile.setOnClickListener { showBottomShet() }
    }

    private fun showBottomShet() {
        val inflater = LayoutInflater.from(requireContext())
        bottomSheetBinding = BottomSheetDialogLayoutProfileBinding.inflate(inflater)
        val bottomSheetDialog = BottomSheetDialog(requireContext(), R.style.RoundedBottomSheetDialogStyle)
        bottomSheetDialog.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.edtUpdateBirthday.keyListener = null
        bottomSheetBinding.edtUpdateBirthday.setOnClickListener { showDatePicker() }
        bottomSheetBinding.btnUpdateProfile.setOnClickListener {
            val name = bottomSheetBinding.edtUpdateName.text.toString()
            val birthdate = bottomSheetBinding.edtUpdateBirthday.text.toString()
            if (name == "" && birthdate == ""){
                Toast.makeText(requireActivity(), "Isian tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }else{
                profileViewModel.updateProfile(name,birthdate)
                profileViewModel.isSuccess.observe(requireActivity()){
                    showToas(it)
                    bottomSheetDialog.dismiss()
                }
            }
        }
        bottomSheetDialog.show()

    }

    private fun showToas(success: Boolean) {
        if (success){
            Toast.makeText(requireActivity(), "Berhasil memperbarui", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireActivity(), "Gagal memperbarui", Toast.LENGTH_SHORT).show()
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
            val inputDate = dataProfile?.birthDate
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val dateFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())

            val date = inputDate?.let { inputFormat.parse(it) }
            val birthdate = dateFormat.format(date!!)
            binding.tvBirthday.text = birthdate
        }

        binding.tvNameTop.text = dataProfile?.name
        binding.tvEmailTop.text = dataProfile?.email
        Glide.with(requireActivity())
            .load(dataProfile?.picture)
            .into(binding.profileImage)
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(selectedYear, selectedMonth, selectedDay)

            val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val formattedDate = dateFormat.format(selectedDate.time)

            bottomSheetBinding.edtUpdateBirthday.setText(formattedDate)
        }, year, month, dayOfMonth)

        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()

        datePickerDialog.show()
    }
}