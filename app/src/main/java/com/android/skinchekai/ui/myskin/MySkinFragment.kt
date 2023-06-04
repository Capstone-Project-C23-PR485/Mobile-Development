package com.android.skinchekai.ui.myskin

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.skinchekai.R
import com.android.skinchekai.adapter.LogSkinAdapter
import com.android.skinchekai.adapter.WawasanAdapter
import com.android.skinchekai.databinding.FragmentMySkinBinding
import com.android.skinchekai.response.LogSkinResponse
import com.android.skinchekai.response.WawasanResponse
import com.android.skinchekai.ui.camera.CameraActivity
import java.io.File

class MySkinFragment : Fragment() {

    private var _binding: FragmentMySkinBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<LogSkinResponse>()
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

        list.addAll(getListWawasan())
        showRecyclerList()
    }
    private fun getListWawasan(): ArrayList<LogSkinResponse> {
        val dataTitle = resources.getStringArray(R.array.data_title)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val listWawasan = ArrayList<LogSkinResponse>()
        for (i in dataTitle.indices) {
            val wawasan = LogSkinResponse(dataTitle[i], dataDescription[i])
            listWawasan.add(wawasan)
        }
        return listWawasan
    }

    private fun showRecyclerList() {
        binding.rvLogskin.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
        val wawasanAdapter = context?.let { LogSkinAdapter(it,list) }
        binding.rvLogskin.adapter = wawasanAdapter
    }
}