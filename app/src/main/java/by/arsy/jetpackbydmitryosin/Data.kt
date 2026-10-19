package by.arsy.jetpackbydmitryosin

data class ShoppingItem(
    val id: Int,
    val name: String,
    var isChecked: Boolean
)
val shoppingItemNames = listOf(
    "banana",
    "apple",
    "milk",
    "fish",
    "meat",
    "pear"
)
fun getShoppingItemList(): List<ShoppingItem> {
    val shoppingItems = mutableListOf<ShoppingItem>()
    repeat(60) { addShoppingItems(shoppingItems) }
    return shoppingItems
}

fun addShoppingItems(shoppingItems: MutableList<ShoppingItem>) {
    shoppingItems.add(
        ShoppingItem(
            id = shoppingItems.size,
            name = shoppingItemNames[shoppingItemNames.indices.random()],
            isChecked = false
        )
    )
}