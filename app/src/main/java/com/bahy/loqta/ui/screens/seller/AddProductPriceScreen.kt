package com.bahy.loqta.ui.screens.seller

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

@Composable
fun AddProductPriceScreen(
    onBack: () -> Unit = {},
    onPublish: () -> Unit = {},
) {
    var price by remember { mutableStateOf("") }
    var negotiable by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = { PublishBottomBar(onPublish = onPublish) },
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onBack) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "رجوع")
                }
                Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("إضافة إعلان", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Text("خطوة 3 من 3", style = MaterialTheme.typography.bodySmall, color = LoqtaGreen)
                }
                Spacer(modifier = Modifier.size(48.dp))
            }
        },
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp),
        ) {
            StepperRow()

            Spacer(modifier = Modifier.height(24.dp))

            SectionHeader(icon = Icons.Default.AttachMoney, title = "حدد السعر المناسب")

            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("السعر المطلوب") },
                placeholder = { Text("0.00") },
                leadingIcon = { Text("ج.م", modifier = Modifier.padding(start = 12.dp), fontWeight = FontWeight.Bold) },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Checkbox(checked = negotiable, onCheckedChange = { negotiable = it })
                Text("السعر قابل للتفاوض")
            }

            Spacer(modifier = Modifier.height(20.dp))

            SectionHeader(icon = Icons.Default.LocationOn, title = "الموقع الجغرافي")

            DropdownField("اختر المدينة", Icons.Default.LocationCity)
            Spacer(modifier = Modifier.height(12.dp))
            DropdownField("اختر الحي / المنطقة", Icons.Default.Map)

            Spacer(modifier = Modifier.height(12.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color(0xFFE8EAF6)),
                contentAlignment = Alignment.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(LoqtaGreenDark),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.MyLocation, contentDescription = null, tint = Color.White)
                }
                OutlinedButton(
                    onClick = {},
                    modifier = Modifier.align(Alignment.BottomStart).padding(12.dp),
                    shape = RoundedCornerShape(8.dp),
                ) {
                    Icon(Icons.Default.MyLocation, contentDescription = null, modifier = Modifier.size(16.dp))
                    Text("  تحديد دقيق", style = MaterialTheme.typography.labelSmall)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            SectionHeader(icon = Icons.Default.Phone, title = "طرق التواصل")

            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                ContactButton("اتصال هاتفي", Icons.Default.Phone, Modifier.weight(1f))
                ContactButton("واتساب", Icons.Default.Chat, Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(12.dp))

            Surface(shape = RoundedCornerShape(12.dp), color = Color(0xFFE3F2FD)) {
                Text(
                    "سيتمكن المشترون من رؤية رقم هاتفك للتواصل معك بخصوص هذا الإعلان.",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodySmall,
                )
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
private fun PublishBottomBar(onPublish: () -> Unit) {
    Surface(shadowElevation = 12.dp) {
        Button(
            onClick = onPublish,
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(16.dp)
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = LoqtaGreenDark),
        ) {
            Icon(Icons.Default.RocketLaunch, contentDescription = null)
            Text("  نشر الإعلان", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun StepperRow() {
    val steps = listOf("التفاصيل", "الصور", "السعر والموقع")
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        steps.forEachIndexed { index, label ->
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.weight(1f),
            ) {
                Box(contentAlignment = Alignment.Center) {
                    if (index < steps.lastIndex) {
                        Box(
                            modifier = Modifier
                                .padding(start = 24.dp)
                                .width(80.dp)
                                .height(2.dp)
                                .background(LoqtaGreen),
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(if (index < 2) LoqtaGreen else LoqtaGreenDark),
                        contentAlignment = Alignment.Center,
                    ) {
                        if (index < 2) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = Color.White, modifier = Modifier.size(16.dp))
                        } else {
                            Text("3", color = Color.White, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                Text(
                    label,
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier.padding(top = 6.dp),
                    fontWeight = if (index == 2) FontWeight.Bold else FontWeight.Normal,
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(bottom = 12.dp),
    ) {
        Icon(icon, contentDescription = null, tint = LoqtaGreenDark)
        Text("  $title", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DropdownField(placeholder: String, icon: androidx.compose.ui.graphics.vector.ImageVector) {
    OutlinedTextField(
        value = "",
        onValueChange = {},
        readOnly = true,
        placeholder = { Text(placeholder) },
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        leadingIcon = { Icon(icon, contentDescription = null) },
        trailingIcon = { Icon(Icons.Default.KeyboardArrowDown, contentDescription = null) },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
    )
}

@Composable
private fun ContactButton(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(LoqtaGreen)
            .padding(vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.White)
            Text("  $label", color = Color.White, fontWeight = FontWeight.Bold)
        }
    }
}
