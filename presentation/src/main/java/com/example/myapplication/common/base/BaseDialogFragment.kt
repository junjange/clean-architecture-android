package com.example.myapplication.common.base

import android.content.Context
import android.content.res.Configuration
import android.util.DisplayMetrics
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

open class BaseDialogFragment : DialogFragment() {

    override fun onStart() {
        super.onStart()
        val displayMetrics = DisplayMetrics()
        dialog?.window?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
        val widthResizeRequired = isTablet(requireContext()) || isLandscape(requireContext())
        val displayWidth = displayMetrics.widthPixels
        val width = if (widthResizeRequired) (displayWidth * 0.6f).toInt() else ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
//        dialog?.window?.setLayout(width,height)
    }

    private fun isTablet(context: Context): Boolean {
        return (context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

    private fun isLandscape(context: Context): Boolean {
        return context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        dismissAllowingStateLoss()
    }

}