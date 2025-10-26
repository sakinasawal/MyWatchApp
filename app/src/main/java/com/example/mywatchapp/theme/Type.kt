package com.example.mywatchapp.theme

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp
import com.example.mywatchapp.R

// Set of Material typography styles to start with
val CustomFontFamily = FontFamily(
    Font(R.font.arial, FontWeight.Normal),
    Font(R.font.arial_bold, FontWeight.Bold),
)

val Typography =
    MyTypography(
        // Wear OS-optimized sizes (conservative, readable on small circular screens)
        h1 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            lineHeight = 24.sp,
        ),
        h2 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            lineHeight = 22.sp,
        ),
        h3 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        h4 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        h5 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        h6 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 11.sp,
            lineHeight = 14.sp,
        ),
        body1 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
        ),
        body2 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 13.sp,
            lineHeight = 18.sp,
        ),
        body3 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
        ),
        body4 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 11.sp,
            lineHeight = 14.sp,
        ),
        subtitle1 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 14.sp,
        ),
        subtitle2 = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 12.sp,
        ),
        button = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        ),
        caption = TextStyle(
            fontFamily = CustomFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            lineHeight = 12.sp,
        )
    )

data class MyTypography(
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val h6: TextStyle,
    val body1: TextStyle,
    val body2: TextStyle,
    val body3: TextStyle,
    val body4: TextStyle,
    val subtitle1: TextStyle,
    val subtitle2: TextStyle,
    val button: TextStyle,
    val caption: TextStyle,
)

fun TextStyle.withUnderline(underline: Boolean): TextStyle {
    return this.copy(
        textDecoration = if (underline) TextDecoration.Underline else TextDecoration.None
    )
}
