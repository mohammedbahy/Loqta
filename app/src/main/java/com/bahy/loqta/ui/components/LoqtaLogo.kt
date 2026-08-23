package com.bahy.loqta.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bahy.loqta.ui.theme.LoqtaCharcoal
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaOrange

@Composable
fun LoqtaLogo(
    modifier: Modifier = Modifier,
    size: Dp = 80.dp,
    showText: Boolean = false,
    textColor: Color = LoqtaGreenDark,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Canvas(modifier = Modifier.size(size)) {
            val w = this.size.width
            val h = this.size.height

            val tagPath = Path().apply {
                moveTo(w * 0.08f, h * 0.15f)
                quadraticTo(w * 0.08f, h * 0.05f, w * 0.22f, h * 0.05f)
                lineTo(w * 0.78f, h * 0.05f)
                lineTo(w * 0.95f, h * 0.5f)
                lineTo(w * 0.78f, h * 0.95f)
                lineTo(w * 0.08f, h * 0.95f)
                close()
            }
            drawPath(tagPath, color = LoqtaGreen, style = Fill)

            drawRoundRect(
                color = LoqtaCharcoal,
                topLeft = Offset(w * 0.28f, h * 0.38f),
                size = Size(w * 0.12f, h * 0.42f),
                cornerRadius = CornerRadius(w * 0.04f),
            )
            drawRoundRect(
                color = LoqtaCharcoal,
                topLeft = Offset(w * 0.28f, h * 0.62f),
                size = Size(w * 0.32f, h * 0.12f),
                cornerRadius = CornerRadius(w * 0.04f),
            )

            drawCircle(
                color = Color.White,
                radius = w * 0.05f,
                center = Offset(w * 0.34f, h * 0.28f),
            )

            val starPath = Path().apply {
                val cx = w * 0.82f
                val cy = h * 0.12f
                val r = w * 0.08f
                moveTo(cx, cy - r)
                lineTo(cx + r * 0.3f, cy - r * 0.3f)
                lineTo(cx + r, cy)
                lineTo(cx + r * 0.3f, cy + r * 0.3f)
                lineTo(cx, cy + r)
                lineTo(cx - r * 0.3f, cy + r * 0.3f)
                lineTo(cx - r, cy)
                lineTo(cx - r * 0.3f, cy - r * 0.3f)
                close()
            }
            drawPath(starPath, color = LoqtaOrange, style = Fill)
        }

        if (showText) {
            Text(
                text = "لقطة",
                color = textColor,
                fontSize = (size.value * 0.35f).sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.BottomCenter),
            )
        }
    }
}

@Composable
fun LoqtaLogoIcon(
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
) {
    LoqtaLogo(modifier = modifier, size = size, showText = false)
}
