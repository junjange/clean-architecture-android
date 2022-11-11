package com.example.myapplication.create_todo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseFragment
import com.example.myapplication.databinding.FragmentCreateTodoBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class CreateTodoFragment : BaseFragment() {

    private val viewModel: CreateTodoViewModel by viewModels()
    private lateinit var viewDataBinding: FragmentCreateTodoBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_create_todo, container, false)
        viewDataBinding = FragmentCreateTodoBinding.bind(root).apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }

        toastMessage = viewModel.toastMessage
        exception = viewModel.exception
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        setupEvent()
    }

    private fun initToolbar() {
        viewDataBinding.toolbar.let {
            it.setNavigationIcon(R.drawable.ic_back)
            it.setNavigationOnClickListener {
                navController.popBackStack()
            }
        }
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.navigationEvent.collectLatest {
                when(it) {
                    is CreateTodoNavigationAction.NavigateToInit -> Unit
                    is CreateTodoNavigationAction.NavigateToAdd -> navController.popBackStack()
                    is CreateTodoNavigationAction.NavigateToBlankError -> showToastMessage("제목이나 본문에 빈칸이 있으면 안됩니다.")
                    else -> {}
                }
            }
        }
    }
}