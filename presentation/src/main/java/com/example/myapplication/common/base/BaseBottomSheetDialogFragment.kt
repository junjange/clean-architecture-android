package com.example.myapplication.common.base

import android.app.Dialog
import android.content.Context
import android.content.res.Configuration
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Gravity
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.myapplication.R
import com.example.myapplication.common.util.doOnApplyWindowInsets
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BaseBottomSheetDialogFragment : BottomSheetDialogFragment() {

    override fun getTheme(): Int = R.style.Theme_MyApplication

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        setWhiteNavigationBar(dialog)

        return dialog
    }

    override fun onStart() {
        super.onStart()
        if (isTablet(requireContext()) || isLandscape(requireContext())) {
            val displayMetrics = DisplayMetrics()
            dialog?.window?.windowManager?.defaultDisplay?.getMetrics(displayMetrics)
            val displayWidth = displayMetrics.widthPixels
            val width = (displayWidth * 0.6f).toInt()
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog?.window?.setLayout(width, height)
        }
    }

    /**
     * https://stackoverflow.com/questions/47553936/prevent-bottomsheetdialogfragment-covering-navigation-bar/51329005#51329005
     **/
    private fun setWhiteNavigationBar(dialog: Dialog) {
        dialog.window?.decorView?.doOnApplyWindowInsets { _, insets, _ ->
            val bottom = insets.systemWindowInsetBottom
            if (bottom > 0) {
                val dimDrawable = GradientDrawable()
                val navigationBarDrawable = GradientDrawable().apply {
                    shape = GradientDrawable.RECTANGLE
                    setColor(ContextCompat.getColor(requireContext(), R.color.default_bottom_sheet_dialog_background))
                }
                val layers = arrayOf<Drawable>(dimDrawable, navigationBarDrawable)
                val windowBackground = LayerDrawable(layers).apply {
                    setLayerHeight(1, bottom)
                    setLayerGravity(1, Gravity.BOTTOM)
                }
                dialog.window?.setBackgroundDrawable(windowBackground)
            }
        }
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
