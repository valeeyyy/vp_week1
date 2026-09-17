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

public fun enterBattle(wizard: Wizard) {
    val enemyType = EnemyType.entries.random()
    val enemy = Enemy(enemyType)

    println("\n——— BATTLE ———")
    while (true) {
        wizard.showStatus()
        println()
        enemy.showStatus()
        println("—————————————")
        println("\ta. Fire Attack")
        println("\tb. Water Attack")
        println("\tc. Grass Attack")
        println("\td. Drink Potion")
        println("\te. Run")
        print("Please select an option: ")
        val choice = readlnOrNull()?.lowercase()

        when (choice) {
            "a" -> doAttack(wizard, EnemyType.FIRE, enemy)
            "b" -> doAttack(wizard, EnemyType.WATER, enemy)
            "c" -> doAttack(wizard, EnemyType.GRASS, enemy)
            "d" -> {
                println("\n——— POTION ———")
                println("1. Health Potion")
                println("2. Mana Potion")
                println("3. Back")
                print("Please select an option: ")
                val choice = readlnOrNull()?.toIntOrNull()
                when (choice) {
                    1 -> {
                        if (wizard.drinkHealthPotion()) {
                            println("Healed 25 HP!\n")
                        } else {
                            println("No health potions left!\n")
                        }
                    }
                    2 -> {
                        if (wizard.drinkManaPotion()) {
                            println("Restored 15 mana!\n")
                        } else {
                            println("No mana potions left!\n")
                        }
                    }
                    else -> return
                }
            }
            "e" -> {
                println("You escaped!\n")
                return
            }
            else -> println("Invalid option.\n")
        }

        if (!enemy.isAlive() || !wizard.isAlive()) {
            break
        }
    }

    if (!wizard.isAlive()) {
        gameOver(wizard)
    } else {
        battleWon(wizard, enemy)
    }
}
public fun doAttack(wizard: Wizard, type: EnemyType, enemy: Enemy) {
    if (wizard.currMana < 10) {
        println("Not enough mana!\n")
    }

    val wizDmg = wizard.attack(type, enemy)
    enemy.takeDamage(wizDmg)
    println("$type attack: $wizDmg damage!")

    if (wizard.isStrong) {
        println("Healed ${wizard.lifeSteal} HP!")
    }

    if (!enemy.isAlive()) {
        return
    }

    val enemyDmg = enemy.attack()
    wizard.takeDamage(enemyDmg)
    println("${enemy.enemyName} hit for $enemyDmg damage!")
}

public fun battleWon(wizard: Wizard, enemy: Enemy) {
    println("You defeated ${enemy.enemyName}!")
    wizard.kill()

    if (wizard.isStrong && wizard.currKills == wizard.maxKills) {
        println("Strong Wizard! HP, Mana & damage +50%.")
        println("Lifesteal unlocked!")
    } else if (wizard.isStrong) {
        println("Lifesteal increased to ${wizard.lifeSteal}!")
    } else if (wizard.currKills >= wizard.maxKills) {
        println("Strong Wizard! HP, Mana & damage +50%.")
        println("Lifesteal unlocked!")
    }
    println("Kills: ${wizard.currKills}/${wizard.maxKills}")
}

public fun gameOver(wizard: Wizard) {
    println("You died! Restarting...\n")
    wizard.reset()
}