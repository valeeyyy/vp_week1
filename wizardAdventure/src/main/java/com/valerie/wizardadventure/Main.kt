package com.valerie.wizardadventure

public fun main() {
    print("What's your name? ")
    val name = readlnOrNull()
    val wizardName = if (name.isNullOrBlank()) "nameless wizard" else name
    val wizard = Wizard(wizardName)

    println("\nGood luck, ${wizard.name}! You're gonna need it!\n")

    while(true) {
        mainMenu(wizard)
    }
}

public fun mainMenu(wizard: Wizard) {
    println("What're you going to do?")
    println("\t1. View Stats")
    println("\t2. Enter battle")
    print("Please select an option: ")
    when(readlnOrNull()?.toIntOrNull()) {
        1 -> viewStats(wizard)
        2 -> enterBattle()
        else -> println("Invalid option, please choose 1 or 2.\n")
    }
}
public fun viewStats(wizard: Wizard) {
    wizard.showStats()
    println("—————————————————————————————")
    println("\ta. Drink Mana Potion")
    println("\tb. Drink Health Potion")
    println("\tc. Rename self")
    println("\td. Back")
    print("Please select an option: ")
    when (readlnOrNull()?.lowercase()) {
        "a" -> {
            if (wizard.drinkManaPotion()) {
                println("Your mana has been restored by 15 points.\n")
            } else {
                println("You have no mana potions left.\n")
            }
        }

        "b" -> {
            if (wizard.drinkHealthPotion()) {
                println("Your health has been restored by 25 points.\n")
            } else {
                println("You have no health potions left.\n")
            }
        }

        "c" -> {
            print("Enter your new name: ")
            val newName = readlnOrNull()
            if (newName.isNullOrBlank()) {
                println("Name cannot be empty.\n")
            } else {
                wizard.renameSelf(newName)
                print("Name updated successfully\n")
            }
        }
        "d" -> return
        else -> println("Invalid option!\n")
    }
}

public fun enterBattle() {

}