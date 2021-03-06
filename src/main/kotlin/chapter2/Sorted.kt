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

val Int.show: String
    get() = "the value of this is $this"

val String.reply
    get() = "hi $this"

fun main() {
    val list = listOf<Int>(3, 2, 3, 4, 5)
    println(Sorted().isSorted(list) { element, next -> next > element })
}