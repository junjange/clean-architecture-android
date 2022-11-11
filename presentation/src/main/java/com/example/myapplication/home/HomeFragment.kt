package com.example.myapplication.home

import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.common.base.BaseFragment
import com.example.myapplication.databinding.FragmentHomeBinding
import com.example.tasam_android.home.HomeNavigationAction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    private lateinit var viewDataBinding: FragmentHomeBinding
    private val navController by lazy { findNavController() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        viewDataBinding = FragmentHomeBinding.bind(root).apply {
            this.viewmodel = viewModel
            this.lifecycleOwner = viewLifecycleOwner
        }
        exception = viewModel.exception
        toastMessage = viewModel.toastMessage
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEvent()
        initAdapter()
        viewModel.getTodoList()
    }

    private fun setupEvent() {
        lifecycleScope.launchWhenStarted {
            viewModel.navigationEvent.collect {
                when(it) {
                    is HomeNavigationAction.NavigateToInit -> Unit
                    is HomeNavigationAction.NavigateToCreate -> navigate(HomeFragmentDirections.actionHomeFragmentToCreateTodoFragment())
                    is HomeNavigationAction.NavigateToTodoDetail -> navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it.todoIdx))
                    is HomeNavigationAction.NavigateToPaging -> navigate(HomeFragmentDirections.actionHomeFragmentToPagingFragment())
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.toggleStateEvent.collectLatest {
                toggleFab(it)
            }
        }

    }



    private fun initAdapter() {
        viewDataBinding.rvTodo.adapter = TodoAdapter(viewModel)
    }

    private fun toggleFab(state: Boolean) {
        // 플로팅 액션 버튼 닫기 - 열려있는 플로팅 버튼 집어넣는 애니메이션
        if (!state) {
            ObjectAnimator.ofFloat(viewDataBinding.btAddTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtAddTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.btToPaging, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.btDeleteTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtDeleteTodo, "translationY", 0f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtToPaging, "translationY", 0f).apply { start() }

            ObjectAnimator.ofFloat(viewDataBinding.btTodo, View.ROTATION, 45f, 0f).apply { start() }
        } else { // 플로팅 액션 버튼 열기 - 닫혀있는 플로팅 버튼 꺼내는 애니메이션
            ObjectAnimator.ofFloat(viewDataBinding.btToPaging, "translationY", -540f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtToPaging, "translationY", -540f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.btAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtAddTodo, "translationY", -360f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.btDeleteTodo, "translationY", -180f).apply { start() }
            ObjectAnimator.ofFloat(viewDataBinding.txtDeleteTodo, "translationY", -180f).apply { start() }

            ObjectAnimator.ofFloat(viewDataBinding.btTodo, View.ROTATION, 0f, 45f).apply { start() }
        }
    }

}