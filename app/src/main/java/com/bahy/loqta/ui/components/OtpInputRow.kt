package com.bahy.loqta.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OtpInputRow(
    otp: String,
    onOtpChange: (String) -> Unit,
    length: Int = 4,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        repeat(length) { index ->
            val char = otp.getOrNull(index)?.toString() ?: ""
            val isFocused = index == otp.length.coerceAtMost(length - 1)

            OutlinedTextField(
                value = char,
                onValueChange = { value ->
                    if (value.length <= 1 && value.all { it.isDigit() }) {
                        val chars = otp.padEnd(length, ' ').toCharArray()
                        if (value.isEmpty()) {
                            if (index < otp.length) {
                                onOtpChange(otp.removeRange(index, index + 1))
                            } else if (otp.isNotEmpty()) {
                                onOtpChange(otp.dropLast(1))
                            }
                        } else {
                            chars[index] = value[0]
                            onOtpChange(chars.concatToString().trim())
                        }
                    }
                },
                modifier = Modifier.size(56.dp),
                textStyle = MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = if (isFocused) Color(0xFF2196F3) else MaterialTheme.colorScheme.primary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f),
                ),
            )
        }
    }
}
