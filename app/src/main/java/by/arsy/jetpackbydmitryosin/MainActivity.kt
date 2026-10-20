package by.arsy.jetpackbydmitryosin

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import by.arsy.jetpackbydmitryosin.ui.theme.JetpackByDmitryOsinTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackByDmitryOsinTheme {
                val saveableShoppingItems = remember {
                    getShoppingItemList().toMutableStateList()
                }

                Scaffold(
                    floatingActionButton = {
                        Button(
                            onClick = { addShoppingItems(saveableShoppingItems) },
                            colors = ButtonDefaults.buttonColors().copy(
                                containerColor = MaterialTheme.colorScheme.primary,
                                contentColor = MaterialTheme.colorScheme.onPrimary
                            )
                        ) {
                            Text(
                                text = "FAB",
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ShoppingItemList(
                        saveableShoppingItems = saveableShoppingItems,
                        onClickItemChecked = { id, checked ->
                            val index = saveableShoppingItems.indexOfFirst { it.id == id }
                            if (index != -1) {
                                saveableShoppingItems[index] =
                                    saveableShoppingItems[index].copy(isChecked = checked)
                            }
                        },
                        modifier = Modifier.padding(paddingValues = innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ShoppingItemList(
    saveableShoppingItems: List<ShoppingItem>,
    modifier: Modifier = Modifier,
    onClickItemChecked: (Int, Boolean) -> Unit = { _, _ -> }
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 8.dp)
    ) {
        items(
            items = saveableShoppingItems,
            key = { it.id }
        ) { shoppingItem ->
            Row(modifier = Modifier.fillMaxWidth()) {

                Text(
                    text = shoppingItem.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.weight(1f))

                Checkbox(
                    checked = shoppingItem.isChecked,
                    onCheckedChange = { checked ->
                        onClickItemChecked.invoke(shoppingItem.id, checked)
                    },
                    colors = CheckboxDefaults.colors().copy(
                        checkedBoxColor = MaterialTheme.colorScheme.background
                    )
                )
            }
        }
    }
}

@Preview(name = "LightMode")
@Preview(
    name = "DarkMode",
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun ShoppingItemListPreview() {
    JetpackByDmitryOsinTheme {
        ShoppingItemList(saveableShoppingItems = mutableListOf(
            ShoppingItem(id = 1, name = shoppingItemNames[0], isChecked = false),
            ShoppingItem(id = 2, name = shoppingItemNames[1], isChecked = true),
            ShoppingItem(id = 3, name = shoppingItemNames[2], isChecked = false),
        ))
    }
}