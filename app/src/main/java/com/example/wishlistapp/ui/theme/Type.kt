package com.example.wishlistapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.wishlistapp.R

val Roboto = FontFamily(
    Font(R.font.roboto_bold , FontWeight.Bold),
    Font(R.font.roboto_regular , FontWeight.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium)
)
val AppTypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = Roboto,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),
    bodySmall = TextStyle(
        fontFamily = Roboto,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    bodyMedium = TextStyle(
        fontFamily = Roboto,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
    )

)

