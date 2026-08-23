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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.AppTextField
import com.bahy.loqta.ui.components.LoqtaLogo
import com.bahy.loqta.ui.components.PasswordTextField
import com.bahy.loqta.ui.components.PrimaryButton
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaTextSecondary

@Composable
fun LoginScreen(
    onLogin: () -> Unit = {},
    onRegister: () -> Unit = {},
    onForgotPassword: () -> Unit = {},
    onGuestLogin: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        LoqtaLogo(size = 72.dp)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "لقطة",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = LoqtaGreenDark,
        )
        Text(
            text = "فرصتك الذكية بدأت هنا",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.extraLarge,
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                AppTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = "الجوال أو البريد الإلكتروني",
                    placeholder = "أدخل بياناتك",
                    leadingIcon = {
                        androidx.compose.material3.Icon(Icons.Default.Person, contentDescription = null)
                    },
                )

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        TextButton(onClick = onForgotPassword) {
                            Text("نسيت كلمة المرور؟", color = MaterialTheme.colorScheme.primary)
                        }
                        Text(
                            text = "كلمة المرور",
                            style = MaterialTheme.typography.labelLarge,
                            modifier = Modifier.align(Alignment.CenterVertically),
                        )
                    }
                    PasswordTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = "",
                        placeholder = "••••••••",
                        leadingIcon = {
                            androidx.compose.material3.Icon(Icons.Outlined.Lock, contentDescription = null)
                        },
                    )
                }

                PrimaryButton(
                    text = "تسجيل الدخول",
                    onClick = onLogin,
                    modifier = Modifier.fillMaxWidth(),
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(
                        text = "  أو عبر  ",
                        style = MaterialTheme.typography.bodySmall,
                        color = LoqtaTextSecondary,
                    )
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
                ) {
                    Text("الدخول عبر جوجل", color = MaterialTheme.colorScheme.onSurface)
                }

                OutlinedButton(
                    onClick = onGuestLogin,
                    modifier = Modifier.fillMaxWidth().height(52.dp),
                    shape = MaterialTheme.shapes.large,
                    border = BorderStroke(1.dp, Color(0xFFB3D4FC)),
                ) {
                    Text("الدخول كضيف", color = MaterialTheme.colorScheme.onSurfaceVariant)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.Center) {
            Text("ليس لديك حساب؟ ", color = MaterialTheme.colorScheme.onSurfaceVariant)
            TextButton(onClick = onRegister) {
                Text("إنشاء حساب جديد", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {
            TextButton(onClick = {}) {
                Text("سياسة الخصوصية", style = MaterialTheme.typography.bodySmall)
            }
            TextButton(onClick = {}) {
                Text("شروط الاستخدام", style = MaterialTheme.typography.bodySmall)
            }
        }

        Text(
            text = "© 2024 لقطة. جميع الحقوق محفوظة.",
            style = MaterialTheme.typography.bodySmall,
            color = LoqtaTextSecondary,
            textAlign = TextAlign.Center,
        )

        Spacer(modifier = Modifier.height(24.dp))
    }
}
