package com.example.myapplication.temp

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseFragment
import com.example.myapplication.databinding.FragmentTempBinding
import com.example.myapplication.temp.TempViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TempFragment : BaseFragment() {
    private val viewModel: TempViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentTempBinding
    private val navController by lazy { findNavController() }

    private var reserveId : String? = null
    private var reserveUserId : Int? = null
    private var userId : String? = null
    private var sportType : String? = null
    private var recruit : String? = null
    private var genderText : String? = null


    private val gender = hashMapOf<String, String>("ALL" to "남녀모두", "MAN" to "남자만", "WOMAN" to "여자만" )
    private val ruleMember = hashMapOf<String, String>("SOCCER" to "11vs11", "FUTSAL" to "6vs6", "RUNNING" to "최대인원", "BASKETBALL" to "8vs8")
    private val shoes = hashMapOf<String, String>("SOCCER" to "풋살화/운동화", "FUTSAL" to "풋살화/운동화", "RUNNING" to "런닝화/운동화", "BASKETBALL" to "농구화/운동화")
    private val reserveStatus = hashMapOf<String, String>("POSSIBLE" to "신청하기" , "IMMINENT" to "신청하기",  "DEADLINE" to "신청 마감" )
    private val stateTextColor = hashMapOf<String, String>("POSSIBLE" to "#FFFFFF" , "IMMINENT" to "#FFFFFF",  "DEADLINE" to "#cccccc" )
    private val stateBtnColor = hashMapOf<String, String>("POSSIBLE" to "#1570ff" , "IMMINENT" to "#1570ff",  "DEADLINE" to "#EEEEEE" )
    private val sdf = SimpleDateFormat("MMMM d일 EEEE", Locale.KOREA)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_temp, container, false)
        viewDataBinding = FragmentTempBinding.bind(root).apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.exception
        toastMessage = viewModel.toastMessage
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        viewModel.getReserve()
        setUpObserver()

    }

    private fun setUpObserver() {
        lifecycleScope.launchWhenStarted {
            launch {
                viewModel.reservesItem.collect {
                    val dateFormat = SimpleDateFormat("yyyy-MM-dd");

                    val  parseDate = dateFormat.parse(it.reserveDate )

                    viewDataBinding.matchDayText.text = sdf.format(parseDate) + " " + it.startT.substring(0, 5) + " ~ " + it.endT.substring(0, 5)
                    viewDataBinding.matchPlaceText.text = it.place
                    viewDataBinding.matchReserveUserNameText.text = it.name
                    viewDataBinding.matchReserveUserStudentIdText.text = it.schoolNum
                    viewDataBinding.matchTitleText.text = it.title
                    viewDataBinding.matchExplanationText.text = it.explanation
                    viewDataBinding.matchGenderText.text = gender[it.gender]
                    viewDataBinding.matchRecruitmentNumText.text = "현재 ${it.currentNum}/${it.recruitmentNum}명"
                    viewDataBinding.matchShoesText.text = shoes[it.sport]
                    viewDataBinding.applyBtn.setCardBackgroundColor(Color.parseColor(stateBtnColor[it.reserveStatus]))
                    viewDataBinding.applyText.text = reserveStatus[it.reserveStatus]
                    viewDataBinding.applyText.setTextColor(Color.parseColor(stateTextColor[it.reserveStatus]))

                }
            }


        }
    }



    private fun initToolbar() {
        with(viewDataBinding.mainToolbar) {
            this.setNavigationIcon(R.drawable.ic_back)
            this.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


}