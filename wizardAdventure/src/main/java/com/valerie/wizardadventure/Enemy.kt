package com.valerie.wizardadventure

import kotlin.math.max

public class Enemy (val enemyType: String) {
    val enemyName = "${enemyType}mon"
    val maxHP = (40..50).random()
    val currHP = maxHP

    public fun isAlive():Boolean {
        return currHP>0
    }

    public fun showStatus() {
        println(enemyName)
        println("HP: ${currHP}/${maxHP}")
        println("Type: ${enemyType}")

    }

    
}