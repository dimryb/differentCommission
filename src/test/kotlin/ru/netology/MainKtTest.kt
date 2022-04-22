package ru.netology

import calcCommission
import checkLimit
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

    @Test
    fun calcCommission_defaultLow() {
        val transferAmount = 500 * ruble

        val commission = calcCommission(transferAmount)

        assertEquals(0, commission)
    }

    @Test
    fun calcCommission_defaultHigh() {
        val transferAmount = 15_000 * ruble

        val commission = calcCommission(transferAmount)

        assertEquals(0, commission)
    }

    @Test
    fun calcCommission_MastercardHigh() {
        val transferAmount = 50_000 * ruble
        val cardType= "MasterCard"
        val totalMonth = 95_000 * ruble

        val commission = calcCommission(transferAmount, cardType, totalMonth)

        assertEquals(320 * ruble, commission)
    }

    @Test
    fun calcCommission_MaestroLow() {
        val transferAmount = 100 * ruble
        val cardType= "Maestro"

        val commission = calcCommission(transferAmount, cardType)

        assertEquals(0 * ruble, commission)
    }

    @Test
    fun calcCommission_MaestroHigh() {
        val transferAmount = 600_000 * ruble
        val cardType= "Maestro"

        val commission = calcCommission(transferAmount, cardType)

        assertEquals(0 * ruble, commission)
    }

    @Test
    fun calcCommission_MastercardLow() {
        val transferAmount = 50_000 * ruble
        val cardType= "MasterCard"
        val totalMonth = 5_000 * ruble

        val commission = calcCommission(transferAmount, cardType, totalMonth)

        assertEquals(0 * ruble, commission)
    }


    @Test
    fun checkLimit_Card_normal() {
        val transferAmount = 5_000 * ruble
        val cardType = "MasterCard"
        val totalMonth = 600_000 * ruble
        val totalDay = 150_000 * ruble

        val checkCode = checkLimit(transferAmount, cardType, totalMonth, totalDay)

        assertEquals(0, checkCode)
    }

    @Test
    fun checkLimit_Card_dayLimit() {
        val transferAmount = 5_000 * ruble
        val cardType = "MasterCard"
        val totalMonth = 600_000 * ruble
        val totalDay = 150_000 * ruble + 1

        val checkCode = checkLimit(transferAmount, cardType, totalMonth, totalDay)

        assertEquals(-1, checkCode)
    }

    @Test
    fun checkLimit_Card_monthLimit() {
        val transferAmount = 5_000 * ruble
        val cardType = "MasterCard"
        val totalMonth = 600_000 * ruble + 1
        val totalDay = 150_000 * ruble

        val checkCode = checkLimit(transferAmount, cardType, totalMonth, totalDay)

        assertEquals(-2, checkCode)
    }

    @Test
    fun checkLimit_Card_simpleTransfer() {
        val transferAmount = 500 * ruble
        val cardType = "Мир"

        val checkCode = checkLimit(transferAmount, cardType)

        assertEquals(0, checkCode)
    }

    @Test
    fun checkLimit_VkPay_normal() {
        val transferAmount = 15_000 * ruble
        val cardType = "VK Pay"
        val totalMonth = 40_000 * ruble

        val checkCode = checkLimit(transferAmount, cardType, totalMonth)

        assertEquals(0, checkCode)
    }

    @Test
    fun checkLimit_VkPay_limit() {
        val transferAmount = 15_000 * ruble + 1
        val cardType = "VK Pay"
        val totalMonth = 40_000 * ruble

        val checkCode = checkLimit(transferAmount, cardType, totalMonth)

        assertEquals(-3, checkCode)
    }

    @Test
    fun checkLimit_VkPay_MonthLimit() {
        val transferAmount = 15_000 * ruble
        val cardType = "VK Pay"
        val totalMonth = 40_000 * ruble + 1

        val checkCode = checkLimit(transferAmount, cardType, totalMonth)

        assertEquals(-4, checkCode)
    }
}