package com.android.skinchekai.ui.myskin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.skinchekai.adapter.LogSkinAdapter
import com.android.skinchekai.databinding.FragmentMySkinBinding
import com.android.skinchekai.response.LogDataItem
import com.android.skinchekai.ui.camera.CameraActivity
import com.android.skinchekai.viewmodel.MySkinViewModel
import com.android.skinchekai.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MySkinFragment : Fragment() {

    private var _binding: FragmentMySkinBinding? = null
    private val mySkinViewModel: MySkinViewModel by viewModels {
        ViewModelFactory.getInstance(requireActivity().application)
    }
    private val binding get() = _binding!!
    companion object {
        const val CAMERA_X_RESULT = 200
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(requireContext(), "Tidak mendapatkan permission.", Toast.LENGTH_SHORT).show()
                activity?.finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMySkinBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnTakeSelfie.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        mySkinViewModel.log.observe(requireActivity()){
            showLog(it)
        }

        getDat()
    }

    private fun getDat() {
        val dayFormat = SimpleDateFormat("dd", Locale.getDefault())
        val dateFormat = SimpleDateFormat("EEE\nMMM yyyy", Locale.getDefault())

        val currentDate = Date()

        val day = dayFormat.format(currentDate)
        val date = dateFormat.format(currentDate)

        binding.tvDay.text = day
        binding.tvDate.text = date

    }

    private fun showLog(dataLog: List<LogDataItem>?) {
        binding.rvLogskin.layoutManager = LinearLayoutManager(requireContext())
        val adapter = dataLog?.let { LogSkinAdapter(requireActivity(),it) }
        binding.rvLogskin.adapter = adapter
    }

}