package com.bahy.loqta.ui.screens.auth

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.bahy.loqta.ui.components.OnboardingIndicator
import com.bahy.loqta.ui.components.PrimaryButton
import com.bahy.loqta.ui.theme.LoqtaBadgeRed
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark
import com.bahy.loqta.ui.theme.LoqtaGreenLight
import com.bahy.loqta.ui.theme.LoqtaOrange

private data class OnboardingPage(
    val title: String,
    val description: String,
    val primaryButton: String,
    val secondaryButton: String? = null,
)

private val onboardingPages = listOf(
    OnboardingPage(
        title = "اعثر على أفضل اللقطات",
        description = "آلاف المنتجات المستعملة بحالة ممتازة وبأسعار لا تقبل المنافسة.\nاكتشف كنزك القادم اليوم في سوقنا الذكي.",
        primaryButton = "التالي",
        secondaryButton = "تخطي",
    ),
    OnboardingPage(
        title = "لقطة موثوقة",
        description = "في لقطة، سلامتك هي أولويتنا. نحن نتحقق من هوية جميع البائعين المتميزين لضمان تجربة تسوق آمنة وموثوقة. تسوق بثقة تامة واحصل على أفضل الصفقات.",
        primaryButton = "المتابعة",
        secondaryButton = "رجوع",
    ),
    OnboardingPage(
        title = "بع واكسب",
        description = "حوّل أغراضك غير المستخدمة إلى أرباح حقيقية في ثوانٍ. التقط صورة، حدد السعر، وابدأ بالبيع لملايين المشترين حولك.",
        primaryButton = "ابدأ الآن",
        secondaryButton = "تخطي التسجيل",
    ),
)

@Composable
fun OnboardingScreen(
    page: Int,
    onNext: () -> Unit,
    onSkip: () -> Unit,
    onBack: () -> Unit = {},
) {
    val currentPage = onboardingPages.getOrElse(page) { onboardingPages.last() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (page == 2) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = "إغلاق",
                    tint = LoqtaGreenDark,
                    modifier = Modifier.size(24.dp),
                )
            } else {
                TextButton(onClick = onSkip) {
                    Text(
                        text = if (page == 0) "تخطي" else "تخطي",
                        color = if (page == 1) MaterialTheme.colorScheme.onSurfaceVariant else LoqtaGreenDark,
                    )
                }
            }
            Text(
                text = "Loqta",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = LoqtaGreenDark,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        when (page) {
            0 -> DealsIllustration()
            1 -> TrustedIllustration()
            else -> SellIllustration()
        }

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = currentPage.title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = currentPage.description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )

        Spacer(modifier = Modifier.weight(1f))

        OnboardingIndicator(
            pageCount = onboardingPages.size,
            currentPage = page,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = currentPage.primaryButton,
            onClick = onNext,
            modifier = Modifier.fillMaxWidth(),
        )

        currentPage.secondaryButton?.let { secondary ->
            Spacer(modifier = Modifier.height(8.dp))
            TextButton(
                onClick = if (page == 1) onBack else onSkip,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                Text(secondary, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

@Composable
private fun DealsIllustration() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(260.dp),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(LoqtaOrange.copy(alpha = 0.15f)),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(LoqtaOrange.copy(alpha = 0.4f), RoundedCornerShape(16.dp)),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(16.dp)
                    .background(LoqtaBadgeRed, RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            ) {
                Text("!لقطة", color = Color.White, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun TrustedIllustration() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .offset(x = 40.dp, y = (-20).dp)
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White),
        )
        Card(
            modifier = Modifier.size(220.dp),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .clip(CircleShape)
                        .background(LoqtaGreen),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(Icons.Default.Shield, contentDescription = null, tint = LoqtaGreenDark, modifier = Modifier.size(48.dp))
                }
                Spacer(modifier = Modifier.height(12.dp))
                Box(
                    modifier = Modifier
                        .background(LoqtaGreenDark, RoundedCornerShape(16.dp))
                        .padding(horizontal = 16.dp, vertical = 6.dp),
                ) {
                    Text("بائع موثوق", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-20).dp, y = 20.dp)
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Verified, contentDescription = null, tint = LoqtaGreen, modifier = Modifier.size(16.dp))
                Text(" أمان 100%", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Composable
private fun SellIllustration() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(280.dp),
        contentAlignment = Alignment.Center,
    ) {
        Card(
            modifier = Modifier
                .size(200.dp)
                .offset(x = 20.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        ) {}
        Card(
            modifier = Modifier.size(220.dp),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .background(Color(0xFFE53935).copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(8.dp)
                            .background(LoqtaGreenDark, RoundedCornerShape(8.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                    ) {
                        Text("التقاط صورة", color = Color.White, style = MaterialTheme.typography.labelSmall)
                    }
                }
                Column(
                    modifier = Modifier.padding(12.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(LoqtaGreen),
                        contentAlignment = Alignment.Center,
                    ) {
                        Icon(Icons.Default.CameraAlt, contentDescription = null, tint = Color.White)
                    }
                    Text("بيع الآن", style = MaterialTheme.typography.labelLarge, fontWeight = FontWeight.Bold)
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = (-10).dp, y = 10.dp)
                .background(LoqtaBadgeRed, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 4.dp),
        ) {
            Text("+ 500 ريال", color = Color.White, fontWeight = FontWeight.Bold, style = MaterialTheme.typography.labelSmall)
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = 10.dp)
                .background(LoqtaGreen, RoundedCornerShape(12.dp))
                .padding(horizontal = 10.dp, vertical = 6.dp),
        ) {
            Text("تم البيع بنجاح! 🎉", color = Color.White, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
        }
    }
}
