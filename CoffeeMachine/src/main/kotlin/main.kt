import java.util.*

enum class Machine(val water: Int, val milk: Int, val coffeeBeans: Int, val money: Int) {
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    companion object {
        fun buy() {
            println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            val input = scanner.nextLine()
            var drink = ESPRESSO

            when (input) {
                "1" -> drink = ESPRESSO
                "2" -> drink = LATTE
                "3" -> drink = CAPPUCCINO
                "back" -> return
            }

            when {
                drink.water > status.water -> println("Sorry, not enough water!\n")
                drink.milk > status.milk -> println("Sorry, not enough milk!\n")
                drink.coffeeBeans > status.coffeeBeans -> println("Sorry, not enough coffee beans!\n")
                status.disposableCups < 1 -> println("Sorry, not enough cups!\n")
                else -> {
                    println("I have enough resources, making you a coffee!\n")
                    status.water -= drink.water
                    status.milk -= drink.milk
                    status.coffeeBeans -= drink.coffeeBeans
                    status.disposableCups--
                    status.money += drink.money
                }
            }
        }

        fun fill() {
            println("Write how many ml of water do you want to add:")
            status.water += scanner.nextInt()
            println("Write how many ml of milk do you want to add:")
            status.milk += scanner.nextInt()
            println("Write how many grams of coffee beans do you want to add:")
            status.coffeeBeans += scanner.nextInt()
            println("Write how many disposable cups of coffee do you want to add:")
            status.disposableCups += scanner.nextInt()
        }

        fun take() {
            println("I gave you $${status.money}")
            status.money = 0
        }

        fun remaining() {
            println("The coffee machine has:\n" +
                    "${status.water} ml of water\n" +
                    "${status.milk} ml of milk\n" +
                    "${status.coffeeBeans} g of coffee beans\n" +
                    "${status.disposableCups} disposable cups\n" +
                    "$${status.money} of money")
        }
    }
}

class Status {
    var water = 400
    var milk = 540
    var coffeeBeans = 120
    var disposableCups = 9
    var money = 550
}

var status = Status()
val scanner = Scanner(System.`in`)

fun main() {
    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        when (scanner.nextLine()) {
            "buy" -> Machine.buy()
            "fill" -> Machine.fill()
            "take" -> Machine.take()
            "remaining" -> Machine.remaining()
            "exit" -> break
        }
    }
}