package chapter2

fun main() {
    println(fibonacci(3))
}

fun <T, R> List<T>.filterOrElse(predicate: (T) -> Boolean, orElse: (T) -> R) {
    val intermediateList = mutableListOf<T>()
    for (element in this) {
        if (predicate(element)) {
            intermediateList.add(element)
        } else orElse(element)
    }
}

/**
tail recursive function to calculate the factorial
 **/
fun factorial(n: Int): Int {
    tailrec fun go(n: Int, acc: Int): Int {
        if (n <= 0) return acc
        else return go(n - 1, acc * n)
    }
    return go(n, 1)
}

fun fibonacci(i: Int): Int {
    tailrec fun go(i: Int, a: Int, b: Int): Int {
        return if (i == 0) a else go(i - 1, b, a + b)
    }
    return go(i, 0, 1)
}

