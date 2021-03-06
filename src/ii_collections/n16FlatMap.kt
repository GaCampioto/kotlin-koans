package ii_collections

fun example() {

    val result = listOf("abc", "12").flatMap { it.toList() }

    result == listOf('a', 'b', 'c', '1', '2')
}

val Customer.orderedProducts: Set<Product> get() = orders.flatMap { it.products }.toHashSet()

val Customer.orderedProductsList: List<Product> get() = orders.flatMap { it.products }

val Shop.allOrderedProducts: Set<Product> get() = customers
            .flatMap { it.orders }
            .flatMap { it.products }
            .toSet()
