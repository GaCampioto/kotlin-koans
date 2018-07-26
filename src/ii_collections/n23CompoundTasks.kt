package ii_collections

fun Shop.getCustomersWhoOrderedProduct(product: Product): Set<Customer> = customers
        .filter { product in it.orderedProducts }
        .toSet()

fun Customer.getMostExpensiveDeliveredProduct(): Product? = orders
        .filter { it.isDelivered }
        .flatMap { it.products }
        .maxBy { it.price }

fun Shop.getNumberOfTimesProductWasOrdered(product: Product): Int = customers
        .flatMap { it.orderedProductsList }
        .filter { it == product }
        .size
