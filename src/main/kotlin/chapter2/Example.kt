package chapter2

import chapter2.Example.abs
import chapter2.Example.formatResult

object Example {
    fun abs(n: Int): Int =
        if (n < 0) -n
        else n

    fun factorial(i: Int): Int {
        fun go(n: Int, acc: Int): Int =
            if (n <= 0) acc
            else go(n - 1, n * acc)
        return go(i, 1)
    }

    fun formatResult(message: String, n: Int, f: (Int) -> Int): String {
        val msg = "The %s of %d is %d"
        return msg.format(message, n, f(n))
    }
}

fun main() {
    println(formatResult("factorial", 7, ::factorial))
    println(formatResult("absolute value", -42, ::abs))
}