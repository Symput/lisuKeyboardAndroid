package com.amosgwa.lisukeyboard.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.inputmethodservice.KeyboardView
import android.support.annotation.ColorInt
import android.support.annotation.DrawableRes
import android.util.AttributeSet
import android.view.*
import android.widget.*
import com.amosgwa.lisukeyboard.R

class CustomKeyView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        var repeatable: Boolean = false,
        var codes: IntArray? = null,
        var label: String? = null,
        var icon: Drawable? = null,
        @ColorInt var textColor: Int? = null,
        var textSize: Float? = null,
        @DrawableRes var keyBackground: Drawable? = ColorDrawable(Color.TRANSPARENT)
) : FrameLayout(context, attrs, defStyleAttr) {

    val keyTextView = CustomKeyTextView(
            context,
            color = textColor,
            size = textSize
    )

    init {
        isClickable = true
        // Set the key background.
        background = keyBackground

        // Center the text view inside the FrameLayout and style the text view.
        val childLayoutParams = FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        childLayoutParams.gravity = Gravity.CENTER
        keyTextView.layoutParams = childLayoutParams

        // Icons get priority in the key view.
        if (icon != null) {
            val imageView = ImageView(context)
            imageView.layoutParams = childLayoutParams
            imageView.setImageDrawable(icon)
            addView(imageView)
        } else {
            keyTextView.text = label
            addView(keyTextView)
        }
    }

    fun updateLabel(newLabel: String) {
        keyTextView.text = newLabel
    }
}

class CustomKeyTextView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0,
        defStyleRes: Int = R.style.CustomKeyDefaultStyle,
        @ColorInt var color: Int? = null,
        var size: Float? = null
) : TextView(context, attrs, defStyleAttr, defStyleRes) {
    init {
        background = ColorDrawable(Color.TRANSPARENT)

        setTextColor(color ?: DEFAULT_TEXT_COLOR)

        textSize = size ?: DEFAULT_TEXT_SIZE
        textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    companion object {
        const val DEFAULT_TEXT_SIZE = 12.0f
        const val DEFAULT_TEXT_COLOR = Color.BLACK
    }
}

class CustomKeyPreview @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = R.style.CustomKeyPreviewDefaultStyle, height: Int
) : TextView(context, attrs, defStyleAttr, defStyleRes) {
    var x = 0
    var y = 0

    init {
        this.height = height
    }
}