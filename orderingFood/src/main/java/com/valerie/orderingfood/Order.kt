package com.valerie.orderingfood

public class Order (
    val customerName: String,
    val orderItems: ArrayList<OrderItem>
) {
    public fun total(): Double {
        var totalPrice = 0.0
        for (orderItem in orderItems) {
            totalPrice += orderItem.subtotal()
        }
        return totalPrice
    }

    public fun printOrder() {
        println("————————  $customerName's ORDER ————————— ")

        for (i in orderItems.indices) {
            val orderItem = orderItems[i]
            println(String.format("%d. %-15s x%-3d $%.2f",
                i + 1, orderItem.item.name, orderItem.quantity, orderItem.subtotal()
            ))
        }
        println("—————————————————————————————————")
        println(String.format("TOTAL%20s$%.2f", "", total()))
    }
}