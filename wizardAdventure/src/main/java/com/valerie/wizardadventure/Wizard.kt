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
    var isStrong = false
    var lifeSteal = 0

    public fun isAlive(): Boolean {
        return currHP > 0
    }
    public fun showStats() {
        println("————————  ${name}'s STATS ————————— ")
        println("HP: ${currHP}/${maxHP}")
        println("Mana: ${currMana}/${maxMana}")
        println("Kills needed to envolve: ${currKills}/${maxKills}")
        println("Mana Potions held: ${manaPotion}")
        println("Health Potions held: ${healthPotion}")
    }

    public fun showStatus() {
        println(name)
        println("HP: ${currHP}/${maxHP}")
        println("Mana: ${currMana}/${maxMana}")
        println("HP Potions: ${healthPotion}")
        println("MP Potions: ${manaPotion}")
    }

    public fun drinkManaPotion(): Boolean {
        if (manaPotion <= 0) {
            return false
        }
        manaPotion--
        currMana += 15
        if (currMana > maxMana) {
            currMana = maxMana
        }
        return true
    }

    public fun drinkHealthPotion(): Boolean {
        if (healthPotion <= 0) {
            return false
        }
        healthPotion--
        currHP += 25
        if (currHP > maxHP) {
            currHP = maxHP
        }
        return true
    }

    public fun renameSelf(newName: String) {
        name = newName
    }

    public fun hasEnoughMana(): Boolean {
        return currMana >= 10
    }

    public fun becomeStrong() {
        isStrong = true
        maxMana = (maxMana * 1.5).toInt()
        currMana = maxMana
        maxHP = (maxHP * 1.5).toInt()
        currHP = maxHP
        lifeSteal = 1
    }

    public fun attack(type: EnemyType, enemy: Enemy): Int {
        currMana -= 10
        var dmg = 10

        if ((type == EnemyType.FIRE && enemy.type == EnemyType.GRASS) ||
            (type == EnemyType.WATER && enemy.type == EnemyType.FIRE) ||
            (type == EnemyType.GRASS && enemy.type == EnemyType.WATER)) {
            dmg *= 2
        }

        if (isStrong) {
            dmg = (dmg * 1.5).toInt()
            currHP += lifeSteal
            if (currHP>maxHP) {
                currHP = maxHP
            }
        }
        return dmg
    }

    public fun takeDamage(dmg: Int) {
        currHP -= dmg
    }

    public fun kill() {
        currKills++
        if (isStrong) {
            lifeSteal++
        } else if (currKills >= maxKills) {
            becomeStrong()
        }
    }

    public fun reset() {
        maxHP = 50
        currHP = 50
        maxMana = 30
        currMana = 30
        manaPotion = 5
        healthPotion = 5
        currKills = 0
        isStrong = false
        lifeSteal = 0
    }
}