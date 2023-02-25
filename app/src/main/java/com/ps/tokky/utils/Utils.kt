package com.ps.tokky.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.RelativeSizeSpan
import com.ps.tokky.models.OTPLength

object Utils {
    fun getThemeColorFromAttr(context: Context, colorAttr: Int): Int {
        val arr = context.theme.obtainStyledAttributes(intArrayOf(colorAttr))
        val colorValue = arr.getColor(0, -1)
        arr.recycle()
        return colorValue
    }
}

fun Int.formatOTP(length: OTPLength): SpannableStringBuilder {
    val res = "$this"
        .padStart(length.value, '0')
        .reversed()
        .replace(".".repeat(length.chunkSize).toRegex(), "$0 ")
        .trim()
        .reversed()

    val split = res.split(" ")
    val spannable = SpannableStringBuilder()
    for (i in split) {
        val span = SpannableString("$i ")
        span.setSpan(RelativeSizeSpan(.5f), i.length, i.length + 1, 0)
        span.setSpan(Typeface.MONOSPACE, 0, i.length, 0)
        spannable.append(span)
    }
    return spannable
}

@SuppressLint("DefaultLocale")
fun String.cleanSecretKey(): String {
    return this.replace("\\s", "").toUpperCase()
}

fun String.isValidSecretKey(): Boolean {
    return Regex(Constants.BASE32_CHARS).matches(this)
}