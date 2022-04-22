const val ruble = 100

fun main() {
    val transferAmount = 500 * ruble
    val cardType = "MasterCard"
    val totalMonth = 80_000 * ruble
    val totalDay = 0

    if (checkLimit(transferAmount, cardType, totalMonth, totalDay) == 0) {
        val commission = calcCommission(transferAmount, cardType, totalMonth)
        println("Commission: $commission")
    }
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



