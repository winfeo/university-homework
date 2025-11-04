package com.homework.hw2_9

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.homework.hw2_9.ui.theme.Hw2_9Theme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    private var totalSum = 0
    private var productSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingCartScreen()
        }
    }
}


@Composable
fun ShoppingCartScreen() {
    var products by remember {
        mutableStateOf(
            listOf(
                Product(0, "Товар #1", 100),
                Product(1, "Товар #2", 150),
                Product(2, "Товар #3", 56)
            )
        )
    }

    val totalSum = products.sumOf { it.price }
    val productSize = products.size

    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        // Отображение списка товаров
        products.forEach { product ->
            Text(text = "${product.name} - ${product.price} рублей")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "Товаров на сумму: $totalSum рублей")

        Spacer(modifier = Modifier.height(24.dp))

        AddProductSection(
            totalSum = totalSum,
            onAdd = {
                products = products + Product(
                    id = products.size,
                    name = "Товар #${products.size + 1}",
                    price = Random.nextInt(0, 100)
                )
            }
        )

        RemoveProductSection(
            productSize = productSize,
            onRemove = {
                products = products.dropLast(1)
            }
        )
    }
}

@Composable
fun AddProductSection(
    totalSum: Int,
    onAdd: () -> Unit
) {
    val context = LocalContext.current

    // Если сумма заказа больше 500, то показываем сообщение о бесплатной доставке
    if (totalSum > 500) {
        android.widget.Toast.makeText(
            context, "Доставка бесплатная!",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    Button(onClick = onAdd) {
        Text(text = "Добавить товар")
    }
}

@Composable
fun RemoveProductSection(
    productSize: Int,
    onRemove: () -> Unit
) {
    if (productSize > 0) {
        Button(onClick = onRemove) {
            Text(text = "Удалить товар")
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ShoppingCartScreenPreview() {
    Hw2_9Theme {
        ShoppingCartScreen()
    }
}