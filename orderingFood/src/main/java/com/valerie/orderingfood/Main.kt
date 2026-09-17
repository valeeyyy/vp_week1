package com.valerie.orderingfood

import kotlin.system.exitProcess

val items: ArrayList<Items> = ArrayList()
val orders: ArrayList<Order> = ArrayList()

public fun main() {
    items.add(Items("Burger", "Beef Burger", 25.0))
    items.add(Items("Pizza", "Cheese Pizza", 30.0))
    items.add(Items("Sushi", "Fresh sushi", 35.0))
    items.add(Items("Ramen", "Chicken ramen", 28.0))
    items.add(Items("Salad", "Fresh vegetable salad", 20.0))

    while (true) {
        mainMenu()
    }
}

public fun mainMenu() {
    println("============ ORDER SYSTEM ============")
    println("1. Make order")
    println("2. View orders")
    println("3. View menu")
    println("4. Add menu")
    println("5. Edit menu")
    println("6. Delete menu")
    println("7. Exit")
    print("Please select an option: ")

    when(readlnOrNull()?.toIntOrNull()) {
        1 -> makeOrder()
        2 -> viewOrders()
        3 -> viewMenu()
        4 -> addMenu()
        5 -> editMenu()
        6 -> deleteMenu()
        7 -> {
            println("Thank You!")
            exitProcess(0)
        }
        else -> println("Invalid option, please choose from 1 to 7.\n")
    }
}

public fun makeOrder() {
    if (items.isEmpty()) {
        println("There are currently no menu items available.\n")
        return
    }

    println("============ MAKE ORDER ============")
    print("Input your name: ")
    val customerName = readlnOrNull().orEmpty()
    if (customerName.isBlank()) {
        println("Customer name cannot be empty.\n")
        return
    }

    val orderItems: ArrayList<OrderItem> = ArrayList()
    var orderAgain = "Y"

    do {
        viewMenu()

        print("Choose menu number: ")
        val choice = readlnOrNull()?.toIntOrNull()

        if (choice == null) {
            println("Menu selection must be a number.\n")
            continue
        }

        if (choice < 1 || choice > items.size) {
            println("That menu number is not available.\n")
            continue
        }

        print("Enter quantity: ")
        val quantity = readlnOrNull()?.toIntOrNull()

        if (quantity == null) {
            println("Quantity must be entered as a number.\n")
            continue
        }

        if (quantity <= 0) {
            println("Quantity must be greater than zero.\n")
            continue
        }

        val selected = items[choice-1]
        var found = false

        for (orderItem in orderItems) {
            if (orderItem.item == selected) {
                orderItem.quantity += quantity
                found = true
            }
        }

        if (!found) {
            orderItems.add(OrderItem(customerName, selected, quantity))
        }

        print("Would you like to order another item? (Y/N): ")
        orderAgain = readlnOrNull()?.firstOrNull()?.uppercaseChar()?.toString() ?: "N"

        if (orderAgain != "Y" && orderAgain != "N") {
            println("Invalid response. The order will be completed.")
            orderAgain = "N"
        }
    } while (orderAgain == "Y")

    if (orderItems.isNotEmpty()) {
        orders.add(Order(customerName, orderItems))
        println("Your order has been placed successfully!\n")
    } else {
        println("No items were selected. The order has been cancelled.\n")
    }
}

public fun viewOrders() {
    if (orders.isEmpty()) {
        println("No orders have been placed yet.\n")
        return
    }

    println("============ ALL ORDERS ============")
    for (order in orders) {
        order.printOrder()
        println()
    }
}
public fun viewMenu() {
    if (items.isEmpty()) {
        println("The menu is currently empty.\n")
        return
    }
    println("============ MENU ============")
    for (i in items.indices) {
        val item = items[i]
        println("${i + 1}. ${item.name} | ${item.desc} | $${item.price}")
    }
    println()
}

public fun addMenu() {
    println("============ ADD MENU ============")
    print("Item name: ")
    val name = readlnOrNull().orEmpty()
    if (name.isBlank()) {
        println("Menu name cannot be empty.\n")
        return
    }

    print("Item description: ")
    val desc = readlnOrNull().orEmpty()
    if (desc.isBlank()) {
        println("Menu description cannot be empty.\n")
        return
    }

    print("Item price: ")
    var price = readlnOrNull()?.toDoubleOrNull()
    if (price == null) {
        println("Price must be entered as a number.\n")
        return
    }
    if (price <= 0) {
        println("Price must be greater than 0.\n")
        return
    }

    items.add(Items(name, desc, price))
    println("The new menu item has been added successfully!\n")
}

public fun editMenu() {
    if (items.isEmpty()) {
        println("There are no menu items available to edit.\n")
        return
    }
    println("============ EDIT MENU ============")
    viewMenu()
    print("Select the menu number to edit: ")
    val choice = readlnOrNull()?.toIntOrNull()

    if (choice==null) {
        println("Menu selection must be a number.\n")
        return
    }

    if (choice < 1 || choice > items.size) {
        println("The selected menu number does not exist.\n")
        return
    }

    val selected = items[choice-1]
    print("New menu name: ")
    val newName = readlnOrNull()
    if (!newName.isNullOrBlank()) {
        selected.name = newName
    }

    print("New menu description: ")
    val newDesc = readlnOrNull()
    if (!newDesc.isNullOrBlank()) {
        selected.desc = newDesc
    }

    print("New menu price: ")
    val newPrice = readlnOrNull()?.toDoubleOrNull()
    if (newPrice == null) {
        println("Price input is not valid.")
    } else if (newPrice <= 0) {
        println("Price must be greater than zero.")
    } else {
        selected.price = newPrice
    }

    println("Menu information has been updated!\n")
}

public fun deleteMenu() {
    if (items.isEmpty()) {
        println("There are no menu items available to delete.\n")
        return
    }
    println("============ DELETE MENU ============")
    viewMenu()

    print("Select the menu number to delete: ")
    val choice = readlnOrNull()?.toIntOrNull()

    if (choice == null) {
        println("Please enter a valid menu number.\n")
        return
    }

    if (choice < 1 || choice > items.size) {
        println("The menu number you entered cannot be found.\n")
        return
    }

    val selected = items[choice-1]
    items.removeAt(choice-1)
    println("'${selected.name}' has been removed from the menu.")
    println("(Existing orders unchanged.)\n")
}

