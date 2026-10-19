package by.arsy.jetpackbydmitryosin

data class Person(
    val id: Int,
    val name: String
)

fun getPeopleList() = List(100) {
        Person(
                id = it,
                name = "name#$it"
        )
}