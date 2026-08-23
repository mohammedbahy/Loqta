package com.bahy.loqta.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberModalBottomSheetState
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
import com.bahy.loqta.ui.theme.LoqtaGreen
import com.bahy.loqta.ui.theme.LoqtaGreenDark

data class ProductFilterState(
    val minPrice: String = "",
    val maxPrice: String = "",
    val condition: String? = null,
    val city: String? = null,
    val category: String? = null,
    val negotiableOnly: Boolean = false,
)

private val conditions = listOf("جديد", "مستعمل", "كالجديد")
private val cities = listOf("الرياض", "جدة", "الدمام", "القاهرة", "الإسكندرية")
private val categories = listOf("إلكترونيات", "أزياء", "المنزل", "مركبات", "رياضة", "أخرى")

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun FilterBottomSheet(
    initialFilters: ProductFilterState = ProductFilterState(),
    onDismiss: () -> Unit,
    onApply: (ProductFilterState) -> Unit,
    onReset: () -> Unit = {},
) {
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var minPrice by remember { mutableStateOf(initialFilters.minPrice) }
    var maxPrice by remember { mutableStateOf(initialFilters.maxPrice) }
    var selectedCondition by remember { mutableStateOf(initialFilters.condition) }
    var selectedCity by remember { mutableStateOf(initialFilters.city) }
    var selectedCategory by remember { mutableStateOf(initialFilters.category) }
    var negotiableOnly by remember { mutableStateOf(initialFilters.negotiableOnly) }

    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = sheetState,
        shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp),
        containerColor = MaterialTheme.colorScheme.surface,
        dragHandle = null,
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .verticalScroll(rememberScrollState()),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextButton(onClick = {
                    minPrice = ""
                    maxPrice = ""
                    selectedCondition = null
                    selectedCity = null
                    selectedCategory = null
                    negotiableOnly = false
                    onReset()
                }) {
                    Text("إعادة تعيين", color = LoqtaGreen)
                }
                Text(
                    text = "تصفية النتائج",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            FilterSectionTitle("نطاق السعر (ج.م)")
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                OutlinedTextField(
                    value = minPrice,
                    onValueChange = { minPrice = it.filter { c -> c.isDigit() } },
                    placeholder = { Text("من") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = filterFieldColors(),
                )
                OutlinedTextField(
                    value = maxPrice,
                    onValueChange = { maxPrice = it.filter { c -> c.isDigit() } },
                    placeholder = { Text("إلى") },
                    modifier = Modifier.weight(1f),
                    singleLine = true,
                    shape = RoundedCornerShape(12.dp),
                    colors = filterFieldColors(),
                )
            }

            Spacer(modifier = Modifier.height(20.dp))
            FilterSectionTitle("حالة المنتج")
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                conditions.forEach { condition ->
                    FilterChip(
                        selected = selectedCondition == condition,
                        onClick = { selectedCondition = if (selectedCondition == condition) null else condition },
                        label = { Text(condition) },
                        colors = filterChipColors(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            FilterSectionTitle("المدينة")
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                cities.forEach { city ->
                    FilterChip(
                        selected = selectedCity == city,
                        onClick = { selectedCity = if (selectedCity == city) null else city },
                        label = { Text(city) },
                        colors = filterChipColors(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            FilterSectionTitle("التصنيف")
            FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                categories.forEach { category ->
                    FilterChip(
                        selected = selectedCategory == category,
                        onClick = { selectedCategory = if (selectedCategory == category) null else category },
                        label = { Text(category) },
                        colors = filterChipColors(),
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            FilterSectionTitle("خيارات إضافية")
            FilterChip(
                selected = negotiableOnly,
                onClick = { negotiableOnly = !negotiableOnly },
                label = { Text("قابل للتفاوض فقط") },
                colors = filterChipColors(),
            )

            Spacer(modifier = Modifier.height(24.dp))
            PrimaryButton(
                text = "تطبيق التصفية",
                onClick = {
                    onApply(
                        ProductFilterState(
                            minPrice = minPrice,
                            maxPrice = maxPrice,
                            condition = selectedCondition,
                            city = selectedCity,
                            category = selectedCategory,
                            negotiableOnly = negotiableOnly,
                        ),
                    )
                    onDismiss()
                },
                modifier = Modifier.fillMaxWidth(),
            )
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun FilterSectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp),
    )
}

@Composable
private fun filterChipColors() = FilterChipDefaults.filterChipColors(
    selectedContainerColor = LoqtaGreenDark,
    selectedLabelColor = Color.White,
    containerColor = Color(0xFFE3F2FD),
)

@Composable
private fun filterFieldColors() = OutlinedTextFieldDefaults.colors(
    unfocusedContainerColor = Color(0xFFF7F8FA),
    focusedContainerColor = Color(0xFFF7F8FA),
)
