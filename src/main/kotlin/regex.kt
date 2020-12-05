import java.math.BigDecimal
import java.math.BigInteger

inline fun <reified T: Any> MatchResult.get(i: Int): T =
    when (T::class) {
        String::class -> groupValues[i] as T
        Int::class -> groupValues[i].toInt() as T
        Long::class -> groupValues[i].toLong() as T
        Float::class -> groupValues[i].toFloat() as T
        Double::class -> groupValues[i].toDouble() as T
        BigInteger::class -> groupValues[i].toBigInteger() as T
        BigDecimal::class -> groupValues[i].toBigDecimal() as T
        Char::class -> (groupValues[i].also { assert(it.length == 1) }.first() as T)
        else -> throw NotImplementedError("missing regex conversion for ${T::class}")
    }

fun String.destructure(regex: String): String? =
    if (Regex(regex).matches(this)) this else null

fun <R> String.destructure(regex: String, transform: () -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform() }

inline fun <reified A : Any>
    String.match(regex: String, condition: (A) -> Boolean): A? =
    Regex(regex).matchEntire(this)?.run { if (condition(get(1))) get(1) else null }

inline fun <reified A : Any, reified B : Any>
    String.match(regex: String, condition: (A, B) -> Boolean): Pair<A,B>? =
    Regex(regex).matchEntire(this)?.run { if (condition(get(1), get(2))) Pair(get(1), get(2)) else null }

inline fun <reified A : Any, reified B : Any, reified C : Any>
    String.match(regex: String, condition: (A, B, C) -> Boolean): Triple<A,B,C>? =
    Regex(regex).matchEntire(this)?.run { if (condition(get(1), get(2), get(3))) Triple(get(1), get(2), get(3)) else null }

inline fun <reified A : Any>
        String.match1(regex: String): A? =
    Regex(regex).matchEntire(this)?.run { get(1) }

inline fun <reified A : Any, reified B : Any>
        String.match2(regex: String): Pair<A,B>? =
    Regex(regex).matchEntire(this)?.run { Pair(get(1), get(2)) }

inline fun <reified A : Any, reified B : Any, reified C : Any>
        String.match3(regex: String): Triple<A,B,C>? =
    Regex(regex).matchEntire(this)?.run { Triple(get(1), get(2), get(3)) }

inline fun <reified A : Any>
    String.destructure(regex: String): A? =
    Regex(regex).matchEntire(this)?.run { get(1) }

inline fun <reified A : Any, reified B : Any>
    String.destructure2(regex: String): Pair<A,B>? =
    Regex(regex).matchEntire(this)?.run { Pair(get(1), get(2)) }

inline fun <reified A : Any, R>
    String.destructure(regex: String, transform: (a: A) -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform(get(1)) }

inline fun <reified A : Any, reified B : Any, R>
    String.destructure(regex: String, transform: (a: A, b: B) -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform(get(1), get(2)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, R>
    String.destructure(regex: String, transform: (a: A, b: B, c: C) -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform(get(1), get(2), get(3)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, reified D : Any, R>
    String.destructure(regex: String, transform: (a: A, b: B, c: C, d: D) -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform(get(1), get(2), get(3), get(4)) }

inline fun <reified A : Any, reified B : Any, reified C : Any, reified D : Any, reified E : Any, R>
    String.destructure(regex: String, transform: (a: A, b: B, c: C, d: D, e: E) -> R): R? =
    Regex(regex).matchEntire(this)?.run { transform(get(1), get(2), get(3), get(4), get(5)) }
