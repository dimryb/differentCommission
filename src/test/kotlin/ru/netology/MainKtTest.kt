package ru.netology

import calcCommission
import org.junit.Test

import org.junit.Assert.*
import ruble

class MainKtTest {

    @Test
    fun calcCommission_MirCommissionLow() {
        val transferAmount = 500 * ruble
        val cardType= "Мир"

        val commission = calcCommission(transferAmount, cardType)

        assertEquals(20 * ruble, commission)
        //assertEquals(100, commission)
    }

    @Test
    fun calcCommission_MirCommissionHigh() {
        val transferAmount = 50_000 * ruble
        val cardType= "Мир"

        val commission = calcCommission(transferAmount, cardType)

        assertEquals(375 * ruble, commission)
    }

    @Test
    fun calcCommission_VkPay() {
        val transferAmount = 50_000 * ruble
        val cardType= "VK Pay"

        val commission = calcCommission(transferAmount, cardType)

        assertEquals(0, commission)
    }
}