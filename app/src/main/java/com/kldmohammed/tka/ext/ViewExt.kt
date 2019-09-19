package com.kldmohammed.tka.ext

import android.view.View
import android.widget.EditText

fun EditText.asString() = text.toString()


fun View.hide() {
    visibility = View.INVISIBLE
}

val View.isVisible: Boolean
    get() = this.visibility == View.VISIBLE


fun View.enable() {
    isEnabled = true
    alpha = 1.0f
}

fun View.disable() {
    isEnabled = false
    alpha = 0.3f
}

fun View.show() {
    visibility = View.VISIBLE
}


fun View.gone() {
    if (isVisible) {
        visibility = View.GONE
    }

}


/*fun Context.toast(message: Int) {
    Toast.makeText(this, message, Toast.LENGTH_LONG)
        .show()
}*/
