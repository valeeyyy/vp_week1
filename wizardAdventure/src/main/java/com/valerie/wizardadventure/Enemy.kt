package com.valerie.wizardadventure

public class Enemy (val type: EnemyType) {
    val enemyName = "${type.name.lowercase().replaceFirstChar { it.uppercase() }}mon"
    val maxHP = (40..50).random()
    var currHP = maxHP

    public fun isAlive():Boolean {
        return currHP > 0
    }
    public fun showStatus() {
        println(enemyName)
        println("HP: ${currHP}/${maxHP}")
        println("Type: ${type.name.lowercase().replaceFirstChar { it.uppercase() }}")
    }

    public fun attack(): Int {
        return 10
    }
    public fun takeDamage(dmg: Int) {
        currHP -= dmg
    }
}