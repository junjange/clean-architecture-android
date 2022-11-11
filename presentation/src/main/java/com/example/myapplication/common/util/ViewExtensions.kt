package com.example.myapplication.common.util

import android.app.Activity
import android.graphics.Outline
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.Observable
import androidx.recyclerview.widget.SimpleItemAnimator
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}


fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.visible(visible: Boolean, isElseGone: Boolean = true) {
    if (visible)
        visible()
    else {
        if (isElseGone)
            gone()
        else
            invisible()
    }
}

fun View.doOnApplyWindowInsets(f: (View, WindowInsetsCompat, ViewPaddingState) -> Unit) {
    // Create a snapshot of the view's padding state
    val paddingState = createStateForView(this)
    ViewCompat.setOnApplyWindowInsetsListener(this) { v, insets ->
        f(v, insets, paddingState)
        insets
    }
    requestApplyInsetsWhenAttached()
}

/**
 * Call [View.requestApplyInsets] in a safe away. If we're attached it calls it straight-away.
 * If not it sets an [View.OnAttachStateChangeListener] and waits to be attached before calling
 * [View.requestApplyInsets].
 */
fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}

private fun createStateForView(view: View) = ViewPaddingState(
    view.paddingLeft,
    view.paddingTop, view.paddingRight, view.paddingBottom, view.paddingStart, view.paddingEnd
)

data class ViewPaddingState(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
    val start: Int,
    val end: Int
)

fun ViewGroup.inflate(layoutRes: Int): View {
    val inflater = LayoutInflater.from(context)
    return inflater.inflate(layoutRes, this, false)
}

fun ViewGroup.findViewByText(text: String): View? {
    val view = arrayListOf<View>()
    findViewsWithText(view, text, View.FIND_VIEWS_WITH_TEXT)
    return view.firstOrNull()
}

inline fun <reified T : Observable> T.addOnPropertyChanged(crossinline callback: (T) -> Unit) =
    object : Observable.OnPropertyChangedCallback() {
        override fun onPropertyChanged(observable: Observable?, i: Int) =
            callback(observable as T)
    }.also { addOnPropertyChangedCallback(it) }

fun SimpleItemAnimator.disableAnimation() {
    changeDuration = 0
    addDuration = 0
    removeDuration = 0
    moveDuration = 0
}

fun Activity.hideSoftKeyboard() {
    (getSystemService(Activity.INPUT_METHOD_SERVICE) as? InputMethodManager)?.run {
        hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }
}

fun TextInputEditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                //offer(text)
                // 값 내보내기
                trySend(text)
            }
        }
        addTextChangedListener(listener)
        // 콜백이 사라질때 실행, 리스너 제거
        awaitClose { removeTextChangedListener(listener) }
    }.onStart {
        // event 방출
        emit(text)
    }
}

