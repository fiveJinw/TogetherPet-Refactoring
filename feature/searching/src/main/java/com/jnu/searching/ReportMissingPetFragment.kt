package com.jnu.searching

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.jnu.model.enums.ReportStatus
import com.jnu.searching.databinding.DateTimePickerBinding
import com.jnu.searching.databinding.ReportMissingPetFragmentBinding
import com.jnu.ui.CustomToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.properties.Delegates

@AndroidEntryPoint
class ReportMissingPetFragment : Fragment() {
    private lateinit var _binding: ReportMissingPetFragmentBinding
    private val binding get() = _binding

    private val reportMissingViewModel: ReportMissingViewModel by viewModels()

    private lateinit var resultLauncher: ActivityResultLauncher<Intent> //선택한 이미지 화면에 띄우기

    private var selectedDateTime: String = "1999년 1월 1일 10:00"
    private var latitude: Double = 37.0
    private var longitude: Double = 131.0
    private var imgUri: Uri? = null

    private var missingId by Delegates.notNull<Long>()

    companion object {
        private const val ARG_MISSING_ID = "missing_id"

        fun newInstance(missingId: Long): ReportMissingPetFragment {
            val fragment = ReportMissingPetFragment()
            val args = Bundle()
            args.putLong(ARG_MISSING_ID, missingId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ReportMissingPetFragmentBinding.inflate(inflater,container,false)

        missingId = arguments?.getLong(ARG_MISSING_ID)
            ?: throw IllegalArgumentException("Missing Id 없음")

        //목격 시간 선택
        binding.reportMissingTime.setOnClickListener {
            setPicker()
        }

        //목격 장소 선택
        binding.reportMissingLocation.setOnClickListener {
            goToSelectLocationFragment()
        }

        return binding.root
    }

    private fun goToSelectLocationFragment() {
        //dialog 형식
        val locationSelectFragment = LocationSelectFragment()
        locationSelectFragment.show(
            requireActivity().supportFragmentManager,
            "LocationSelectFragment"
        )
    }

    private fun setPicker() {
        val pickerBinding = DateTimePickerBinding.inflate(layoutInflater)

        //오늘 날짜 표기
        val calendar = Calendar.getInstance()
        val dateFormat = SimpleDateFormat("yyyy년 M월 d일", Locale.getDefault())
        pickerBinding.pickerNowDate.text = dateFormat.format(calendar.time)

        //time picker 설정
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(pickerBinding.root)
        val dialog = builder.create()
        dialog.show()

        //date picker 설정
        pickerBinding.datePickerBtn.setOnClickListener {

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                requireContext(),
                { _, selectedYear, selectedMonth, selectedDay ->
                    calendar.set(selectedYear, selectedMonth, selectedDay)
                    pickerBinding.pickerNowDate.text = dateFormat.format(calendar.time)
                },
                year, month, day
            )
            datePickerDialog.show()
        }

        pickerBinding.confirmButton.setOnClickListener {
            val hour =
                pickerBinding.timePicker.hour
            val minute =
                pickerBinding.timePicker.minute

            Log.d("TimePicker", "Selected Hour: $hour")
            Log.d("TimePicker", "Selected Minute: $minute")

            val selectedDate = pickerBinding.pickerNowDate.text.toString()
            val selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)

            selectedDateTime = "$selectedDate $selectedTime"
            Log.d("yeong", selectedDateTime)

            binding.reportMissingTime.apply {
                text = selectedDateTime
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
            }

            dialog.dismiss()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // resultLauncher 초기화
        resultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            Log.d("testt", "$result, ${result.resultCode}, ${result.data}")
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let { uri ->
                    Glide.with(this)
                        .load(uri)
                        .into(binding.reportMissingImg)
                    imgUri = uri
                }
            }
        }

        //이미지 업로드
        binding.imgUpload.setOnClickListener {
            setImage()
        }

        // 제보할 데이터 전달 받기
        parentFragmentManager.setFragmentResultListener("locationRequestKey", this) { _, bundle ->
            Log.d("BundleCheck", "[Report Suspected] Bundle Content: $bundle")

            latitude = bundle.getDouble("latitude", 37.0)
            longitude = bundle.getDouble("longitude", 131.0)
            val address = bundle.getString("address") ?: "Unknown Address"

            Log.d(
                "ReceiveCheck",
                "[Report Suspected] Received Latitude: $latitude, Longitude: $longitude, 주소: $address"
            )

            binding.reportMissingLocation.apply {
                text = address
                setTextColor(ContextCompat.getColor(requireContext(), android.R.color.black))
            }
        }

        //'제보 하기' 클릭
        binding.missingRegisterButton.setOnClickListener {
            Log.d("yeong", "제보 하기 클릭 됨")
            sendReport()
            sendCheck()
        }

    }

    private fun setImage() {
        Log.d("testt", "setImage")
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
        }
        resultLauncher.launch(intent)
    }

    private fun sendReport() {
        val color = binding.reportMissingColor.text.toString()
        val gender = binding.reportMissingGender.text.toString()
        val species = binding.reportMissingSpecies.text.toString()
        val info = binding.reportMissingReportBtn.text.toString()
        val absolutePath = imgUri

        Log.d("sendReport", "Absolute Path: $absolutePath")

        if (absolutePath != null) {
            val file = getFileFromUri(absolutePath)
            val fileList = listOf(file)

            Log.d("sendReport", "File: $file")
            Log.d("sendReport", "File List: $fileList")

            if (file.exists() && file.length() > 0) {
                Log.d("sendReport", "File 정상: ${file.absolutePath}")

                reportMissingViewModel.reportMissingObserve(
                    color = color,
                    gender = gender,
                    breed = species,
                    description = info,
                    foundLongitude = longitude,
                    foundLatitude = latitude,
                    foundDate = selectedDateTime,
                    file = fileList,
                    missingId = missingId
                )
            } else {
                Log.e("sendReport", "File 에러")
            }
        }
    }

    fun getFileFromUri(uri: Uri?): File {
        val contentResolver = requireContext().contentResolver
        val inputStream = uri?.let { contentResolver.openInputStream(it) } ?: return File("null")
        val file = File(requireContext().cacheDir, "temp_image.jpg")
        file.outputStream().use { output ->
            inputStream.copyTo(output)
        }
        return file
    }

    private fun sendCheck() {
        viewLifecycleOwner.lifecycleScope.launch {
            reportMissingViewModel.reportStatus.collect { status ->
                when (status) {
                    ReportStatus.SUCCESS -> {
                        context?.let {
                            val messageS = requireContext().getString(com.jnu.ui.R.string.report_success)
                            CustomToast.displayToast(it, messageS)
                        }
                        parentFragmentManager.popBackStack()
                    }

                    ReportStatus.ERROR -> {
                        context?.let {
                            val messageF = requireContext().getString(com.jnu.ui.R.string.fail)
                            CustomToast.displayToast(it, messageF)
                        }
                    }

                    else -> {
                        // IDLE 상태
                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}