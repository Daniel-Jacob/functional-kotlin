sealed class List<out T> {
    companion object {

        fun <T> of(vararg elements: T): List<T> {
            val tail = elements.sliceArray(1 until elements.size)
            return if (elements.isEmpty()) Nil else Cons(elements[0], of(*tail))
        }

        fun <A> tail(xs: List<A>): List<A> = when (xs) {
            is Nil -> throw IllegalStateException("cannot return tail of nil")
            is Cons -> xs.tail
        }

        fun <A> setHead(xs: List<A>, x: A): List<A> = when (xs) {
            is Nil -> throw IllegalStateException("cannot set head on nil list")
            is Cons -> Cons(x, xs.tail)
        }

        fun <A> drop(l: List<A>, n: Int): List<A> {
            return if (n == 0) l else when (l) {
                is Nil -> throw IllegalStateException("cannot drop from nil list")
                is Cons -> drop(l.tail, n - 1)
            }
        }

        fun <A> dropWhile(l: List<A>, f: (A) -> Boolean): List<A> {
            return when (l) {
                is Nil -> l
                is Cons -> if (f(l.head)) dropWhile(l.tail, f) else l
            }
        }

        fun sum(xs: List<Int>): Int = when (xs) {
            is Nil -> 0
            is Cons -> xs.head + sum(xs.tail)
        }

        fun product(xs: List<Double>): Double = when (xs) {
            is Nil -> 1.0
            is Cons -> xs.head * product(xs.tail)
        }

        fun <A> init(l: List<A>): List<A> = when (l) {
            is Nil -> throw IllegalStateException("cannot init nil list")
            is Cons -> if (l.tail == Nil) Nil else Cons(l.head, init(l.tail))
        }

        fun <A> empty(): List<A> = Nil

        fun <A, B> foldRight(xs: List<A>, initialValue: B, f: (A, B) -> B): B =
            when (xs) {
                is Nil -> initialValue
                is Cons -> {
                    f(xs.head, foldRight(xs.tail, initialValue, f))
                }
            }

        fun <A, B> foldLeftR(xs: List<A>, z: B, f: (B, A) -> B): B =
            foldRight(
                xs,
                { b: B -> b },
                { a, g ->
                    { b ->
                        g(f(b, a))
                    }
                })(z)

        fun <A, B> foldRightL(xs: List<A>, z: B, f: (A, B) -> B): B =
            foldLeft(xs,
                { b: B -> b },
                { g, a ->
                    { b ->
                        g(f(a, b))
                    }
                })(z)

        //expanded example

        tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B = when (xs) {
            is Nil -> z
            is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
        }

        fun <A, B> sum2(aList: List<Int>): Int = foldRight(aList, 0, { a, b -> a + b })

        fun <A, B> product2(aList: List<Int>): Int = foldRight(aList, 1, { a, b -> a * b })

        fun <A> length(xs: List<A>): Int = when (xs) {
            is Nil -> 0
            is Cons -> foldRight(xs, 0, { _, b -> b + 1 })
        }

        fun <A, B> sum3(aList: List<Int>): Int = foldLeft(aList, 0) { a, b -> a + b }

        fun <A, B> product3(aList: List<Int>): Int = foldLeft(aList, 1) { a, b -> a * b }

        fun <A> reverse(aList: List<A>): List<A> = foldLeft(aList, empty(), { b: List<A>, a -> Cons(a, b) })

        fun <A> append(aList: List<A>, anotherList: List<A>) = foldRight(aList, anotherList, { a, b: List<A> -> Cons(a, b) })

        fun transformInt(aList: List<Int>): List<Int> = foldRight(aList, empty(), { a, b -> Cons(a + 1, b) })

        fun transformDouble(aList: List<Double>): List<String> = foldRight(aList, empty(), { a, b -> Cons(a.toString(), b) })

        fun <A, B> map(xs: List<A>, f: (A) -> B): List<B> = foldRight(xs, empty(), { a, b -> Cons(f(a), b) })


        fun <A> filter(xs: List<A>, f: (A) -> Boolean): List<A> = foldRightL(xs, empty(), { a, b -> if (f(a)) Cons(a, b) else xs })


        fun <A, B> flatMap(xa: List<A>, f: (A) -> List<B>): List<B> = TODO()

    }


    object Nil : List<Nothing>()

    data class Cons<out T>(
        val head: T,
        val tail: List<T>
    ) : List<T>()
}

fun main() {
}