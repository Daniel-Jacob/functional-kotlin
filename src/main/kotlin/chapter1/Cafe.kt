package chapter1

import java.math.BigDecimal

class Cafe {


    fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {

        val cup = Coffee(BigDecimal.ONE)

        return Pair(cup, Charge(cc, cup.price))

    }

    fun buyCoffees(cc: CreditCard, n: Int): Pair<List<Coffee>, Charge> {
        val purchases = List(n) { buyCoffee(cc) }
        val (coffees, charges) = purchases.unzip()
        return Pair(coffees, charges.reduce { acc, charge -> acc.combine(charge) })
    }

    data class Charge(val creditCard: CreditCard, val amount: BigDecimal) {
        fun combine(other: Charge): Charge {
            if (creditCard == other.creditCard) return Charge(creditCard, amount + other.amount)
            else throw Exception("cannot combine charges to different cards")
        }
    }

    fun List<Charge>.coalesce(): List<Charge> {
        return this.groupBy { it.creditCard }.values.map { it.reduce { acc, charge -> acc.combine(charge) } }
    }

    data class CreditCard(val number: Long)

    data class Coffee(val price: BigDecimal)
}
