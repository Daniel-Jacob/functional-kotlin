package chapter2

class Sorted {
    val <T> List<T>.tail: List<T>
        get() = drop(1)

    val <T> List<T>.head: T
        get() = first()

    fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
        tailrec fun loop(n: Int): Boolean {
            return when {
                n > aa.lastIndex -> true
                order(aa[n], aa[n + 1]) -> loop(n + 1)
                else -> false
            }
        }
        return loop(0)
    }
}

/* adds an extension property to int
note that these extensions are evaluated statically meaning that the property or function
to be evaluated is determined by the actual type and not the type at runtime.
 */

// returns a partial function by passing a total function

fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C =
    { b -> f(a, b) }


// (A) ->(B) -> (C) can be read as two functions one that takes an a and returns a B and a second that takes a C and returns a C
fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C = { a -> { b -> f(a, b) } }

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C = { a, b -> f(a)(b) }

fun <A, B, C> compose(f: (B) -> C, g: (A) -> B): (A) -> C = { gAfterF -> f(g(gAfterF)) }
val Int.show: String
    get() = "the value of this is $this"

val String.reply
    get() = "hi $this"

fun main() {
    val list = listOf<Int>(3, 2, 3, 4, 5)
    println(Sorted().isSorted(list) { element, next -> next > element })
    val result = curry { a: String, b: String -> args(a, b) }
    println(result("1")("2"))
}

fun args(arg1: String, arg2: String): String = arg2