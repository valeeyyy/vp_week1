package com.valerie.orderingfood

public data class OrderItem (
    val customerName: String,
    val item: Items,
    var quantity: Int
) {
    public fun subtotal(): Double {
        return item.price * quantity
    }
}