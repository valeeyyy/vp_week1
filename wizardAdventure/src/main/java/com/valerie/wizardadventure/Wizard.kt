package com.valerie.wizardadventure

public class Wizard(var name: String) {
    var maxHP = 50
    var currHP = 50
    var maxMana = 30
    var currMana = 30
    var maxKills = 5
    var currKills = 0
    var manaPotion = 5
    var healthPotion = 5

    public fun showStats() {
        println("————————  ${name}'s STATS ————————— ")
        println("HP: ${currHP}/${maxHP}")
        println("Mana: ${currMana}/${maxMana}")
        println("Kills needed to envolve: ${currKills}/${maxKills}")
        println("Mana Potions held: ${manaPotion}")
        println("Health Potions held: ${healthPotion}")
    }

    public fun drinkManaPotion(): Boolean {
        if (manaPotion<=0) {
            return false
        }
        manaPotion--
        currMana += 15
        if (currMana>maxMana) {
            currMana = maxMana
        }
        return true
    }

    public fun drinkHealthPotion(): Boolean {
        if (healthPotion<=0) {
            return false
        }
        healthPotion--
        currHP += 25
        if (currHP>maxHP) {
            currHP = maxHP
        }
        return true
    }

    public fun renameSelf(newName: String) {
        name = newName
    }
}