package com.example.myapplication.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseFragment
import com.example.myapplication.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    private val viewModel: DetailViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentDetailBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_detail, container, false)
        viewDataBinding = FragmentDetailBinding.bind(root).apply {
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
        val args: DetailFragmentArgs by navArgs()
        viewModel.todoIdxEvent.value = args.todoIdx
        viewModel.getTodo()
        setEvent()
    }

    private fun initToolbar() {
        with(viewDataBinding.toolbar) {
            this.setNavigationIcon(R.drawable.ic_back)
            this.setNavigationOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun setEvent(){
        lifecycleScope.launchWhenStarted {
            viewModel.navigationEvent.collect{
                when(it){
                    DetailNavigationActions.NavigateDelete ->{
                        navController.popBackStack()
                    }
                    DetailNavigationActions.NavigatePatch -> {
                        navController.popBackStack()
                    }
                    DetailNavigationActions.NavigateBlankError -> {
                        Toast.makeText(requireActivity(), "Title 혹은 Body가 비어있습니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}