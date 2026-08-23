package com.bahy.loqta.ui.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.AppTextField
import com.bahy.loqta.ui.components.LoqtaLogo
import com.bahy.loqta.ui.components.PasswordTextField
import com.bahy.loqta.ui.components.PrimaryButton
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaTextSecondary

@Composable
fun RegisterScreen(
    onRegister: () -> Unit = {},
    onLogin: () -> Unit = {},
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LoqtaLogo(size = 64.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "إنشاء حساب جديد",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = LoqtaGreenDark,
        )
        Text(
            text = "انضم إلى مجتمع 'لقطة' وابدأ في اكتشاف العروض المميزة اليوم.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            AppTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = "الاسم الكامل",
                placeholder = "أدخل اسمك بالكامل",
                leadingIcon = {
                    androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = null)
                },
            )
            AppTextField(
                value = email,
                onValueChange = { email = it },
                label = "البريد الإلكتروني",
                placeholder = "example@domain.com",
                leadingIcon = {
                    androidx.compose.material3.Icon(Icons.Default.Email, contentDescription = null)
                },
            )
            AppTextField(
                value = phone,
                onValueChange = { phone = it },
                label = "رقم الهاتف",
                placeholder = "05XXXXXXXX",
                leadingIcon = {
                    androidx.compose.material3.Icon(Icons.Default.Phone, contentDescription = null)
                },
            )
            PasswordTextField(
                value = password,
                onValueChange = { password = it },
                label = "كلمة المرور",
                placeholder = "********",
                leadingIcon = {
                    androidx.compose.material3.Icon(Icons.Outlined.Lock, contentDescription = null)
                },
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "إنشاء حساب",
            onClick = onRegister,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            HorizontalDivider(modifier = Modifier.weight(1f))
            Text(
                text = "  أو من خلال  ",
                style = MaterialTheme.typography.bodySmall,
                color = LoqtaTextSecondary,
            )
            HorizontalDivider(modifier = Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f).height(48.dp),
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, Color(0xFF1877F2)),
            ) {
                Text("فيسبوك", color = Color(0xFF1877F2))
            }
            OutlinedButton(
                onClick = {},
                modifier = Modifier.weight(1f).height(48.dp),
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
            ) {
                Text("جوجل")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            Text("لديك حساب بالفعل؟ ", color = MaterialTheme.colorScheme.onSurfaceVariant)
            TextButton(onClick = onLogin) {
                Text("تسجيل الدخول", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}
