const val ruble = 100

fun main() {
    val commission = calcCommission(500 * ruble, "MasterCard", 80_000 * ruble)

    testDifferentCommission()
}

fun calcCommission(transferAmount: Int, cardType: String = "VK Pay", totalMonth: Int = 0): Int {
    return when (cardType) {
        "MasterCard", "Maestro" -> {
            val transferredLimit = 75_000 * ruble
            val interestRate = 0.006
            val fixCommission = 20 * ruble
            if (totalMonth < transferredLimit) 0 else (transferAmount * interestRate + fixCommission).toInt()
        }
        "Visa", "Мир" -> {
            val interestRate = 0.0075
            val minCommission = 20 * ruble
            val commission = transferAmount * interestRate
            if (commission < minCommission) minCommission else commission.toInt()
        }
        else -> 0 // "VK Pay"
    }
}

fun checkLimit(transferAmount: Int, cardType: String, totalMonth: Int = 0, totalDay: Int = 0): Int {
    val limitDay = 150_000 * ruble
    val limitMonth = 600_000 * ruble
    val limitVkPay = 15_000 * ruble
    val limitVkPayMonth = 40_000 * ruble

    return when {
        (totalDay > limitDay) -> -1
        (totalMonth > limitMonth) -> -2
        ((cardType == "VK Pay") && (transferAmount > limitVkPay)) -> -3
        ((cardType == "VK Pay") && (totalMonth > limitVkPayMonth)) -> -4
        else -> 0
    }
}

fun testDifferentCommission() {
    moneyTransfer(500 * ruble, "MasterCard", 80_000 * ruble)
    moneyTransfer(500 * ruble, "MasterCard", 0 * ruble)
    moneyTransfer(500 * ruble, "Мир")
    moneyTransfer(500 * ruble, "Мир", totalDay = 150_001 * ruble)
    moneyTransfer(500 * ruble, "Visa", totalMonth = 600_001 * ruble)
    moneyTransfer(15_001 * ruble, "VK Pay")
    moneyTransfer(15_000 * ruble, "VK Pay", totalMonth = 43_000 * ruble)
}

fun moneyTransfer(transferAmount: Int, cardType: String, totalMonth: Int = 0, totalDay: Int = 0) {
    when (val checkCode = checkLimit(transferAmount, cardType, totalMonth, totalDay)) {
        0 -> {
            val commission = calcCommission(transferAmount, cardType, totalMonth)
            println(
                "Комиссия за перевод ${rubleToString(transferAmount)} по карте $cardType составляет " +
                        "${rubleToString(commission)} при сумме за месяц ${rubleToString(totalMonth)}"
            )
        }
        -1 -> println("Перевод ${rubleToString(transferAmount)} по карте $cardType не возможен: превышен суточный лимит")
        -2, -4 -> println("Перевод ${rubleToString(transferAmount)} по карте $cardType не возможен: превышен месячный лимит")
        -3 -> println("Перевод ${rubleToString(transferAmount)} по карте $cardType не возможен: превышена максимальная сумма перевода")
        else -> println("Перевод ${rubleToString(transferAmount)} по карте $cardType не возможен, код: $checkCode")
    }
}

fun rubleToString(amount: Int): String {
    return String.format("%d.%02d₽", amount / ruble, amount % ruble)
}


